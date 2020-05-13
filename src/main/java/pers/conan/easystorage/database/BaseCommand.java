package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;

import java.util.Collection;

/**
 * 类：基本命令
 *
 * @author Conan
 */
public abstract class BaseCommand implements Executable<BaseCommand>, Command<BaseCommand> {

    @Override
    public abstract BaseCommand select(String table);

    @Override
    public abstract BaseCommand select(String table, String condition, Object[] args);

    @Override
    public abstract BaseCommand select(String table, String condition, String sort, Object[] args);

    @Override
    public abstract BaseCommand Select(String sql, Object[] args);

    @Override
    public abstract BaseCommand insert(String table, Structure target);

    @Override
    public abstract BaseCommand insert(String table, Structure target, String seq);

    @Override
    public abstract BaseCommand insert(String table, Collection<Structure> targets);

    @Override
    public abstract BaseCommand insert(String table, Collection<Structure> targets, String seq);

    @Override
    public abstract BaseCommand insert(String table, Collection<Structure> targets, String seq, boolean isParallel);

    @Override
    public abstract BaseCommand update(String table, Structure target);

    @Override
    public abstract BaseCommand update(String table, Structure target, String condition, Object[] args);

    @Override
    public abstract BaseCommand update(String table, Collection<Structure> targets);

    @Override
    public abstract BaseCommand delete(String table, Structure target);

    @Override
    public abstract BaseCommand delete(String table, Structure target, String condition, Object[] args);

    @Override
    public abstract BaseCommand delete(String table, Collection<Structure> targets);

    @Override
    public abstract BaseCommand Execute();
}
