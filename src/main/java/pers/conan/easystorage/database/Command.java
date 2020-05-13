package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;

import java.util.Collection;
import java.util.List;

/**
 * 接口：SQL命令
 *
 * @author Conan
 */
public interface Command {

    void select(String table);

    void select(String table, String condition, Object[] args);

    void insert(String table, Structure target);

    void insert(String table, Structure target, String seq);

    void insert(String table, Collection<Structure> targets);

    void insert(String table, Collection<Structure> targets, String seq);

    void insert(String table, Collection<Structure> targets, String seq, boolean isParallel);

    void update(String table, Structure target);

    void update(String table, Structure target, String condition, Object[] args);

    void update(String table, Collection<Structure> targets);

    void delete(String table, Structure target);

    void delete(String table, Structure target, String condition, Object[] args);

    void delete(String table, Collection<Structure> targets);

}
