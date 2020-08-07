package com.iwangzhe.commonlibs.mod.io.file.serv;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.base.ServApi;
import com.iwangzhe.commonlibs.mod.Mgr;
import com.iwangzhe.commonlibs.mod.io.file.IoFileMain;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class IoFileServApi extends ServApi {
    private static IoFileServApi instance = null;

    protected IoFileServApi(IoFileMain main) {
        super(main);
    }

    public static IoFileServApi getInstance(IoFileMain main) {
        if (instance == null) {
            instance = new IoFileServApi(main);
        }
        return instance;
    }

    public String readFileStr(String filePath) {
        try {
            String resStr = "";
            String encoding = "UTF8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    resStr = resStr.concat(lineTxt);
                }
                read.close();
                return resStr;
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return null;
    }

    public String readFileStr(InputStream is) {
        String res = "";
        try {
            byte[] buf = new byte[is.available()];
            int n = is.read(buf);
            if (n > 0) {
                res = new String(buf, "UTF8");      //  必须将GBK码制转成Unicode
            }
            is.close();
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    /**
     * 合并
     *
     * @param path
     * @param fileList
     * @param deleteHead
     * @param callback
     * @return
     */
    public String mergeFiles(String path, List fileList, int deleteHead, IMergeCallback callback) {
        File resFile = new File(path);
        FileOutputStream fileOutputStream = null;
        try {
            if (!resFile.exists()) {
                resFile.createNewFile();
            }
            fileOutputStream = new FileOutputStream(resFile);
            for (int i = 0; i < fileList.size(); i++) {
                File file = new File((String) fileList.get(i));
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] myByte = new byte[fileInputStream.available()];
                //文件长度
                int length = myByte.length;
                //头文件
                if (i == 0) {
                    while (fileInputStream.read(myByte) != -1) {
                        fileOutputStream.write(myByte, 0, length);
                    }
                } else {
                    while (fileInputStream.read(myByte) != -1) {
                        fileOutputStream.write(myByte, deleteHead, length - deleteHead);
                    }
                }
                fileOutputStream.flush();
                fileInputStream.close();
            }
            //结束后关闭流
            fileOutputStream.close();
            if (callback != null) {
                callback.onSuccess();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 对文件进行加密
     *
     * @param filePath 文件保存路径
     * @return
     */
    public String encryptFile(String filePath) {
        String result = "";
        File file = new File(filePath);
        FileInputStream inputFile = null;
        long size = 0;
        try {
            inputFile = new FileInputStream(file);
            size = inputFile.available();
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            String encodedString = Base64.encodeToString(buffer, Base64.NO_WRAP);
            result = encodedString;
            Log.e("Base64", "Base64---->" + encodedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对文件进行解密
     *
     * @param encodedString 需要解密的字符串
     * @param filePath      保存文件的路径
     */
    public void decodeFile(String encodedString, String filePath) {
        if (TextUtils.isEmpty(encodedString)) {
            return;
        }
        FileOutputStream fos = null;
        try {
            byte[] decodeBytes = Base64.decode(encodedString.getBytes(), Base64.DEFAULT);
            File desFile = new File(filePath);
            fos = new FileOutputStream(desFile);
            fos.write(decodeBytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存bitmap图片数据并回调
     *
     * @param bitmap   图片数据
     * @param path     保存的路径
     * @param fileName 保存图片名称
     * @param callback 回调函数
     */
    public void saveBitmap(final Bitmap bitmap, final String path, final String fileName, final ISaveCallback callback) {
        if (bitmap == null)
            return;
        // 必须先创建目录才能创建文件
        // 目录不存在就创建
        final File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Mgr.getHandler().post(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmaptemp = bitmap;
                BufferedOutputStream bos;
                try {
                    File myCaptureFile = new File(dir, fileName);
                    bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                    if (bitmaptemp.compress(Bitmap.CompressFormat.PNG, 100, bos)) {
                        bos.flush();
                        bos.close();
                        if (null != bitmaptemp && !bitmaptemp.isRecycled()) {
                            bitmaptemp.recycle();
                            callback.onSuccess(path + "/" + fileName);
                            System.gc();
                        }
                    }
                } catch (Exception e) {
                } finally {
                    bos = null;
                }
            }
        });
    }

    public void saveGif(final String url, final String storePath, final String fileName, final ISaveCallback callback) {
        new AsyncTask<Void, Void, byte[]>() {
            @Override
            protected byte[] doInBackground(Void... params) {
                byte[] gifbyte = null;
                HttpURLConnection conn = null;
                try {
                    URL imgUrl = new URL(url);
                    conn = (HttpURLConnection) imgUrl.openConnection();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    InputStream in = conn.getInputStream();
                    if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        // 连接不成功
                        return null;
                    }

                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    gifbyte = out.toByteArray();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    conn.disconnect();
                }
                return gifbyte;

            }

            protected void onPostExecute(byte[] gifbyte) {
                // 首先保存图片
                File appDir = new File(storePath);
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                File file = new File(appDir, fileName);
                if (!file.exists()) {
                    file = new File(appDir, fileName);
                }
                if (gifbyte == null || gifbyte.length == 0) {
                    return;
                }
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    fos.write(gifbyte);
                    callback.onSuccess(storePath + fileName);
                } catch (Exception e) {
                } finally {
                    try {
                        fos.close();
                    } catch (Exception e) {
                    }
                }
            }
        }.execute();
    }

    /**
     * 下载图片
     *
     * @param url 图片地址
     * @return Bitmap 图片
     */
    public Bitmap getBitmapFromUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        byte[] bitmapByte = null;
        Bitmap bmp = null;
        InputStream inputStream = null;
        try {
            URL url1 = new URL(url);
            URLConnection connection = url1.openConnection();
            connection.setConnectTimeout(8000);
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inPurgeable = true;
                bitmapByte = getByteFromStream(inputStream);
                // 这种解码方式在较低版本上也能正常解码
                bmp = BitmapFactory.decodeByteArray(bitmapByte, 0,
                        bitmapByte.length, o);
                return bmp;
            }
        } catch (Exception e) {
            return null;
        } catch (OutOfMemoryError err) {
            System.gc();
            return null;
        } finally {
            bitmapByte = null;
            inputStream = null;
        }
        return null;
    }

    public byte[] getByteFromStream(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        while ((len = in.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        baos.close();
        in.close();
        return baos.toByteArray();
    }
}
