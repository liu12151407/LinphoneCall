package com.hg.sfern.robot.plugin.generallibrary;

import java.util.Arrays;

/**
 * ***********************************************
 * 包路径：com.hg.sfern.robot.plugin.generallibrary
 * 类描述：bit帮助类
 * 创建人：Liu Yinglong[PHONE：13281160095]
 * 创建时间：2022/02/24
 * 修改人：
 * 修改时间：2022/02/24
 * 修改备注：https://blog.csdn.net/wodeyuer125/article/details/45100319/
 * ***********************************************
 */
public class BitTools {
    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */
    public static byte[] getBooleanArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    /**
     * 把byte转为字符串的bit
     */
    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

    /**
     * 二进制字符串转byte
     */
    public static byte decodeBinaryString(String byteStr) {
        int re, len;
        if (null == byteStr) {
            return 0;
        }
        len = byteStr.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        // 8 bit处理
        if (len == 8) {
            // 正数
            if (byteStr.charAt(0) == '0') {
                re = Integer.parseInt(byteStr, 2);
            } else {// 负数
                re = Integer.parseInt(byteStr, 2) - 256;
            }
        } else {
            // 4 bit处理
            re = Integer.parseInt(byteStr, 2);
        }
        return (byte) re;
    }

    public static void main(String[] args) {
        // 0011 0101
        byte b = 0x35;
        // 输出 [0, 0, 1, 1, 0, 1, 0, 1]
        System.out.println(Arrays.toString(getBooleanArray(b)));
        // 输出 00110101
        System.out.println(byteToBit(b));
        // JDK自带的方法，会忽略前面的 0
        System.out.println(Integer.toBinaryString(0x35));
    }

    /**
     * byte数组定长分段截取
     *
     * @param data
     * @param start
     * @param length
     * @return
     */
    public static byte[] byteSub(byte[] data, int start, int length) {
        byte[] bt = new byte[length];

        if (start + length > data.length) {
            bt = new byte[data.length - start];
        }

        for (int i = 0; i < length && (i + start) < data.length; i++) {
            bt[i] = data[i + start];
        }
        return bt;
    }
}
