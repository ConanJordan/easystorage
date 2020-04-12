package pers.conan.easystorage.util;

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
}
