package com.wjd.myssm.util;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2023/1/12
 */
public class StringUtil {
    //判断字符串是否为null或者""
    public static boolean isEmpty(String str){
        return str==null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
