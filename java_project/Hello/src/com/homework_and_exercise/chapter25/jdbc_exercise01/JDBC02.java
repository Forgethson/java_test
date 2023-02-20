package com.homework_and_exercise.chapter25.jdbc_exercise01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/24
 * 推荐的连接数据库与配置代码方式
 */
public class JDBC02 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

        // 通过 Properties 对象获取文件配置信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\MySQLConfig.properties"));

        // 获取相关值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        // 注册驱动并生成一个新的driver类
        Class.forName(driver);

        // 得到 Connection 对象
        Connection connect = DriverManager.getConnection(url, user, password);

        // 得到 Statement 对象
        Statement statement = connect.createStatement();

        // 执行sql语句
        // 插入数据 insert
//        String sql = "insert into actor values(null, '刘德华', '男', '1961-9-27', '110')";
//        String sql = "insert into actor values(null, '张学友', '男', '1961-7-10', '120')";
//        String sql = "insert into actor values(null, '周星驰', '男', '1962-6-22', '123')";
//        int rows = statement.executeUpdate(sql);
//        System.out.println(rows > 0 ? "sql语句执行成功" : "sql语句执行失败");

        // 查询数据 select
        String sql = "select id, name, sex, borndate from actor";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            Date date = resultSet.getDate("borndate");

            System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
        }




        // 关闭连接资源 ResultSet, Statement 和 Connection
        resultSet.close();
        statement.close();
        connect.close();
    }
}
