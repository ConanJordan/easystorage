package pers.conan.easystorage.util;

import java.util.stream.Stream;

/**
 * 工具类:SQL
 */
public class Sql {

    /**
     * 释放数据库资源
     * @param items
     */
    public static void close(AutoCloseable[] items) {
        Stream.of(items)
                .forEach(item -> {
                    try {
                        item.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

}
