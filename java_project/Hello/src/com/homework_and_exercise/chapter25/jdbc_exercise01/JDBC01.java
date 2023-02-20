package com.homework_and_exercise.chapter25.jdbc_exercise01;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/24
 * <p>
 * 测试JDBC，完成简单操作
 * 1.注册驱动
 * 2.得到连接
 * 3.执行sql
 * 4.关闭连接资源
 */
public class JDBC01 {
    public static void main(String[] args) throws SQLException {

        // 注册驱动（数据库的驱动）
        Driver driver = new Driver();

        // 得到连接信息 url
        // （指定IP:端口，连接的数据库名称）
        String url = "jdbc:mysql://localhost:3306/db02";

        // 将用户名和密码放入 Properties 对象
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");

        // 得到 Connection 对象
        Connection connect = driver.connect(url, properties);

        // 得到 Statement 对象
        Statement statement = connect.createStatement();

        // 执行sql语句
//        String sql = "insert into actor values(null, '刘德华', '男', '1970-11-11', '110')";
//        String sql = "insert into actor values(null, '王謇达', '男', '1998-10-27', '2720')";
        String sql = "insert into actor values(null, '王羽', '女', '1999-10-01', '9069')";
        int rows = statement.executeUpdate(sql);

        System.out.println(rows > 0 ? "sql语句执行成功" : "sql语句执行失败");

        // 关闭连接资源 Statement 和 Connection
        statement.close();
        connect.close();

    }
}
