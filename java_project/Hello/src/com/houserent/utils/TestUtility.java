package com.houserent.utils;

/**
 * 一个测试类，测试 Utility 类功能
 */
public class TestUtility {
    public static void main(String[] args) {

        // 要求输入一个字符串，最大长度为10，如果用户直接回车，就给一个默认值
        String s = Utility.readString(10, "Hello");
        System.out.println("s=" + s);

        // 为何直接用类名直接调用方法？
        // 当一个方法是static（静态方法）（本质是一个全局函数，不会涉及到类的属性）时，可以直接通过类名调用
    }
}
