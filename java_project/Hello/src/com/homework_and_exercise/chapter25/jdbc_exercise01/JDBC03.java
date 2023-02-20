package com.homework_and_exercise.chapter25.jdbc_exercise01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/24
 * 在上一步的基础上，使用PreparedStatement来杜绝SQL注入风险
 */
public class JDBC03 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("======查询数据库现存用户======");
        System.out.print("请输入用户名：");
        String admin_name = scanner.nextLine();
        System.out.print("请输入密码：");
        String admin_pwd = scanner.nextLine();

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

        // 查询数据的 SQL 语句（从admin数据库中查询用户是否存在）
        String sql = "select name, pwd from admin where name = ? and pwd = ?";

        // 得到 PreparedStatement 对象
        PreparedStatement preparedStatement = connect.prepareStatement(sql);

        // 给 sql 中的 ? 赋值
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("恭喜，登陆成功");
        } else {
            System.out.println("对不起，登陆失败");
            return;
        }

        // 创建一个新表
        sql = "create table table1(id int primary key auto_increment, `name` varchar(25) not null default'')";
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.executeUpdate(sql);

        // 关闭连接资源 ResultSet, Statement 和 Connection
        resultSet.close();
        preparedStatement.close();
        connect.close();
    }
}
