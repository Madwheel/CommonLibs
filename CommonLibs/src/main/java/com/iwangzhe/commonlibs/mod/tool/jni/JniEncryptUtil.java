package com.iwangzhe.commonlibs.mod.tool.jni;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述：
 * 作者：小辉
 * 时间：2019/10/17
 */
public class JniEncryptUtil {
    /**
     * 加密请求参数
     *
     * @param params 请求参数
     * @return 加密字符串
     */
    public static String encryptParams(String params) {
        try {
            System.loadLibrary("encrypts");
        } catch (Exception ex) {
        }
        //key为固定值
        String key = "d5p6KjKVMKpVb58wpsbjB2JxfhmsGa9s";
        return encrypt(params, key);
    }

    /**
     * 加密数据
     *
     * @param data 数据
     * @param key  key文本
     * @return 加密字符串
     */
    private static String encrypt(String data, String key) {
        try {
            //将数据转为Base64格式
            String base64 = Base64.encodeToString(data.getBytes(), Base64.NO_WRAP);
            //调用C的加密方法，加密数据
            byte[] encryptData = encrypts(base64.getBytes(), base64.length(), key.getBytes(), key.length());
            //返回加密数据的MD5值
            return encrypt_MD5(encryptData);
        } catch (Exception ex) {
        }
        return "";
    }

    /**
     * 获得加密数据的	MD5值
     *
     * @param encryptData 加密数据
     * @return 加密数据的MD5值
     */
    private static String encrypt_MD5(byte[] encryptData) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(encryptData);
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException ex) {
        }
        return "";
    }

    /**
     * MD5计算
     *
     * @param byteArray 数据
     * @return MD5值
     */
    private static String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }

    /**
     * 调用C签名的方法
     *
     * @param data   参数数据
     * @param orgLen 参数长度
     * @param key    关键字
     * @param keyLen 关键字长度
     * @return 加密数据
     */
    public native static byte[] encrypts(byte[] data, int orgLen, byte[] key, int keyLen);
}
