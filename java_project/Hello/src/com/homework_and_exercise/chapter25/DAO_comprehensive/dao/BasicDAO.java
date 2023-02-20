package com.homework_and_exercise.chapter25.DAO_comprehensive.dao;

import com.homework_and_exercise.chapter25.DAO_comprehensive.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/25
 * 编写DAO的基类，写有公共的方法
 * 数据操纵语言（Data Manipulation Language, DML）
 */
public class BasicDAO<T> {
    private QueryRunner qr = new QueryRunner();

    // 通用的 dml 方法
    public int update(String sql, Object... parameters) {

        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            // parameters 是占位符
            return qr.update(connection, sql, parameters);  // 返回的受影响的行数

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    // 查询：多行多列结果
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameter) {

        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(clazz), parameter);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    // 查询：单行多列结果
    public T querySingle(String sql, Class<T> clazz, Object... parameter) {

        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new BeanHandler<T>(clazz), parameter);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    // 查询：单行单列结果
    public Object queryScalar(String sql, Object... parameter) {

        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql, new ScalarHandler(), parameter);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }
}
