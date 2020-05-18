package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;

import java.util.Collection;

/**
 * 接口：SQL命令
 *
 * @author Conan
 */
public interface Command<T> {

    T select(String table, Class<? extends Structure> structure) throws Exception;

    T select(String table, String condition, Object[] args, Class<? extends Structure> structure) throws Exception;

    T select(String table, String condition, String sort, Object[] args, Class<? extends Structure> structure) throws Exception;

    T Select(String sql, Object[] args, Class<? extends Structure> structure) throws Exception;

    T insert(String table, Structure target, Class<? extends Structure> structure) throws Exception;

    T insert(String table, Structure target, String seq, Class<? extends Structure> structure) throws Exception;

    T insert(String table, Collection<Structure> targets, Class<? extends Structure> structure) throws Exception;

    T insert(String table, Collection<Structure> targets, String seq, Class<? extends Structure> structure) throws Exception;

    T insert(String table, Collection<Structure> targets, String seq, boolean isParallel, Class<? extends Structure> structure) throws Exception;

    T update(String table, Structure target) throws Exception;

    T update(String sql, Object[] args) throws Exception;

    T update(String table, Structure target, String condition, Object[] args) throws Exception;

    T update(String table, Collection<? extends Structure> targets) throws Exception;
    
    T delete(String sql, Object[] args) throws Exception;

    T delete(String table, Structure target, Class<? extends Structure> structure) throws Exception;

    T delete(String table, String condition, Object[] args) throws Exception;

    T delete(String table, Collection<? extends Structure> targets, Class<? extends Structure> structure) throws Exception;

}
