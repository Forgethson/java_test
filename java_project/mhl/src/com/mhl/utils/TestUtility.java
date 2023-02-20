package com.mhl.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 一个测试类，测试 Utility 类功能
 */
public class TestUtility {
    public static void main(String[] args) throws SQLException {

        // 要求输入一个字符串，最大长度为10，如果用户直接回车，就给一个默认值
        String s = Utility.readString(10, "Hello");
        System.out.println("s=" + s);

        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
    }
}
