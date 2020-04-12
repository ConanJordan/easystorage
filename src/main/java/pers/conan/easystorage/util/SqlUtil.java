package pers.conan.easystorage.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

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
                if (! ParseUtil.isEmpty(items[i])) {
                    items[i].close();
                }
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
            if (! ParseUtil.isEmpty(connection)) {
                connection.close();
            }
            if (! ParseUtil.isEmpty(rs)) {
                prst.close();
            }
            if (! ParseUtil.isEmpty(rs)) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭对象(PreparedStatement等)
     * @param items
     */
    public static void close(Collection<AutoCloseable> items) {
        for (AutoCloseable item : items) {
            if (! ParseUtil.isEmpty(item)) {
                try {
                    item.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
