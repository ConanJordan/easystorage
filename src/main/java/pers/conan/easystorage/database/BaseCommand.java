package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;

import java.util.Collection;

/**
 * 类：基本命令
 *
 * @author Conan
 */
public abstract class BaseCommand implements Executable<BaseCommand>, Command<BaseCommand> {

    public abstract BaseCommand select(String table) throws Exception;

    public abstract BaseCommand select(String table, String condition, Object[] args) throws Exception;

    public abstract BaseCommand select(String table, String condition, String sort, Object[] args) throws Exception;

    public abstract BaseCommand Select(String sql, Object[] args) throws Exception;

    public abstract BaseCommand insert(String table, Structure target);

    public abstract BaseCommand insert(String table, Structure target, String seq);

    public abstract BaseCommand insert(String table, Collection<Structure> targets);

    public abstract BaseCommand insert(String table, Collection<Structure> targets, String seq);

    public abstract BaseCommand insert(String table, Collection<Structure> targets, String seq, boolean isParallel);

    public abstract BaseCommand update(String table, Structure target);

    public abstract BaseCommand update(String table, Structure target, String condition, Object[] args);

    public abstract BaseCommand update(String table, Collection<Structure> targets);

    public abstract BaseCommand delete(String table, Structure target);

    public abstract BaseCommand delete(String table, Structure target, String condition, Object[] args);

    public abstract BaseCommand delete(String table, Collection<Structure> targets);

    public abstract BaseCommand Execute();
}
