package com.iwangzhe.commonlibs.mod.biz.device.serv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.iwangzhe.commonlibs.base.CommonRes;
import com.iwangzhe.commonlibs.base.EErrorCode;
import com.iwangzhe.commonlibs.base.IResCallback;
import com.iwangzhe.commonlibs.base.JBase;
import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.base.ServApi;
import com.iwangzhe.commonlibs.mod.biz.constants.AppConstants;
import com.iwangzhe.commonlibs.mod.biz.device.BizDeviceMain;
import com.iwangzhe.commonlibs.mod.biz.device.model.JLaunch;
import com.iwangzhe.commonlibs.mod.io.kvdb.IoKvdbMain;
import com.iwangzhe.commonlibs.mod.net.http.NetHttpMain;
import com.snappydb.SnappydbException;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/415:22
 * desc   :
 */
public class BizDeviceServApi extends ServApi {
    private BizDeviceMain mMain;

    public BizDeviceServApi(BizDeviceMain main) {
        super(main);
        mMain = main;
    }

    private static BizDeviceServApi mBizDeviceServApi = null;

    public static BizDeviceServApi getInstance(BizDeviceMain main) {
        synchronized (BizDeviceServApi.class) {
            if (mBizDeviceServApi == null) {
                mBizDeviceServApi = new BizDeviceServApi(main);
            }
        }
        return mBizDeviceServApi;
    }

    /**
     * 获得新服务器时间戳，例如：1234567890.1234
     *
     * @return 服务器时间戳
     */
    public String getCurrentWZST(double diff) {
        long surrentWZST = (long) (System.currentTimeMillis() / 1000 + diff / 1000);
        return String.valueOf(surrentWZST);
    }

    public void reqClientLaunch(String deviceUuid, String plat, final IResCallback<JLaunch> callback) {
        Map<String, String> params = new HashMap<>();
        params.put("uuid", deviceUuid);
        params.put("plat", plat);
        NetHttpMain.getInstance().getServApi().reqGetResByWzApi(JLaunch.class, AppConstants.CLIENT_LAUNCH, params, new IResCallback<JLaunch>() {
            public void onFinish(CommonRes<JLaunch> res) {
                callback.onFinish(res);
            }
        });
    }

    /**
     * 取SHA1
     *
     * @param data 数据
     * @return 对应的hash值
     */
    public byte[] getHashByString(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.reset();
            messageDigest.update(data.getBytes("UTF-8"));
            return messageDigest.digest();
        } catch (Exception e) {
            return "".getBytes();
        }
    }

    //需要获得READ_PHONE_STATE权限，>=6.0，默认返回null
    @SuppressLint("MissingPermission")
    public String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备的AndroidId
     *
     * @param context 上下文
     * @return 设备的AndroidId
     */
    public String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备序列号（如：WTK7N16923005607）, 个别设备无法获取
     *
     * @return 设备序列号
     */
    public String getSERIAL() {
        try {
            return Build.SERIAL;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备硬件uuid
     * 使用硬件信息，计算出一个随机数
     *
     * @return 设备硬件uuid
     */
    public String getDeviceUUID() {
        try {
            String dev = "3883756" +
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.HARDWARE.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.SERIAL.length() % 10;
            return new UUID(dev.hashCode(),
                    Build.SERIAL.hashCode()).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 转16进制字符串
     *
     * @param data 数据
     * @return 16进制字符串
     */
    public String bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        String stmp;
        for (int n = 0; n < data.length; n++) {
            stmp = (Integer.toHexString(data[n] & 0xFF));
            if (stmp.length() == 1)
                sb.append("0");
            sb.append(stmp);
        }
        return sb.toString().toUpperCase(Locale.CHINA);
    }

    /**
     * 计算系统时间与本地时间差
     *
     * @param wzst 服务器时间(秒)
     * @return 时间差 ，例如：90.1234
     */
    public double getDateInterval(double wzst) {
        Date dt = new Date();
        Log.d("xxx", "getDateInterval: " + wzst + " " + dt.getTime());
        double nowTime = (double) (dt.getTime()) / 1000;
        return wzst - nowTime;
    }

    public CommonRes<JBase> setCurLaunchToDb(JLaunch item) {
        d("setCurLaunch", mMain.getModName());
        try {
            IoKvdbMain.getInstance().getGlobalDb().put(mMain.getModName() + ":curLaunch", item);
        } catch (SnappydbException e) {
            return new CommonRes<>(false);
        }
        return new CommonRes<>(true);

    }

    public CommonRes<JLaunch> getCurLaunchFromDb() {
        JLaunch info;
        try {
            info = IoKvdbMain.getInstance().getGlobalDb().getObject(mMain.getModName() + ":curLaunch", JLaunch.class);
        } catch (SnappydbException e) {
            return new CommonRes<>(false);
        }
        if (info == null) {
            return new CommonRes<>(true, EErrorCode.NO_OBJECT.ordinal());
        }
        return new CommonRes<>(true, 0, info);
    }

}
