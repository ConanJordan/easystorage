package pers.conan.easystorage.util;

import java.util.stream.Stream;

import org.apache.log4j.Logger;

/**
 * 工具类:SQL
 */
public class Sql {
    
    private static final Logger LOG = Logger.getLogger(Sql.class);

    /**
     * 释放数据库资源
     * @param items
     */
    public static void close(AutoCloseable[] items) {
        Stream.of(items)
                .forEach(item -> {
                    try {
                        if (item != null) {
                            item.close();
                            LOG.debug("以下数据库资源已释放:" + item);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

}
