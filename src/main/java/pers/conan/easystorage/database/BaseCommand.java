package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;

import java.sql.SQLException;
import java.util.Collection;

/**
 * 类：基本命令
 *
 * @author Conan
 */
public abstract class BaseCommand implements Executable<BaseCommand>, Command<BaseCommand> {

    @Override
    public abstract BaseCommand select(String table, Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand select(String table, String condition, Object[] args, Class<? extends Structure> structure)
            throws Exception;

    @Override
    public abstract BaseCommand select(String table, String condition, String sort, Object[] args,
            Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand select(String sql, Object[] args, Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand insert(String table, Structure target, Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand insert(String table, Structure target, String seq, Class<? extends Structure> structure)
            throws Exception;

    @Override
    public abstract BaseCommand insert(String table, Collection<Structure> targets, Class<? extends Structure> structure)
            throws Exception;

    @Override
    public abstract BaseCommand insert(String table, Collection<Structure> targets, String seq,
            Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand insert(String table, Collection<Structure> targets, String seq, boolean isParallel,
            Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand update(String table, Structure target, Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand update(String table, Structure target, String condition, Object[] args, Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand update(String table, Collection<? extends Structure> targets, Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand update(String sql, Object[] args, Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand delete(String sql, Object[] args) throws Exception;

    @Override
    public abstract BaseCommand delete(String table, Structure target, Class<? extends Structure> structure) throws Exception;

    @Override
    public abstract BaseCommand delete(String table, String condition, Object[] args) throws Exception;

    @Override
    public abstract BaseCommand delete(String table, Collection<? extends Structure> targets, Class<? extends Structure> structure)
            throws Exception;

    @Override
    public abstract BaseCommand execute() throws SQLException;

}
