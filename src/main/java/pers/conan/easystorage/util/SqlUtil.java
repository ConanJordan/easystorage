package pers.conan.easystorage.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 类：SQL相关元素的工具类
 */
public class SqlUtil {

    /**
     * 关闭对象(Connection, PreparedStatement, ResultSet等)
     * @param items
     */
    public static void close(AutoCloseable[] items) {
        for (int i = 0; i < items.length; i ++) {
            try {
                items[i].close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭对象(Connection, PreparedStatement, ResultSet等)
     * @param connection
     * @param prst
     * @param rs
     */
    public static void close(Connection connection, PreparedStatement prst, ResultSet rs) {
        try {
            connection.close();
            prst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
