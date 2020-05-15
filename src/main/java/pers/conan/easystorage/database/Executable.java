package pers.conan.easystorage.database;

import java.sql.SQLException;

/**
 * 接口：可执行的
 *
 * @author Conan
 */
public interface Executable<T> {
    T execute() throws SQLException;
}
