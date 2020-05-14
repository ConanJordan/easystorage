package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;

import java.util.Collection;
import java.util.List;

/**
 * 接口：SQL命令
 *
 * @author Conan
 */
public interface Command<T> {

    T select(String table) throws Exception;

    T select(String table, String condition, Object[] args) throws Exception;

    T select(String table, String condition, String sort, Object[] args) throws Exception;

    T Select(String sql, Object[] args) throws Exception;

    T insert(String table, Structure target);

    T insert(String table, Structure target, String seq);

    T insert(String table, Collection<Structure> targets);

    T insert(String table, Collection<Structure> targets, String seq);

    T insert(String table, Collection<Structure> targets, String seq, boolean isParallel);

    T update(String table, Structure target);

    T update(String table, Structure target, String condition, Object[] args);

    T update(String table, Collection<Structure> targets);

    T delete(String table, Structure target);

    T delete(String table, Structure target, String condition, Object[] args);

    T delete(String table, Collection<Structure> targets);

}
