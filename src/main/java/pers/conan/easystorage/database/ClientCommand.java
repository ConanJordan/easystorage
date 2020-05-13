package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;

import java.util.Collection;

/**
 * 类：客户端命令
 */
public class ClientCommand extends BaseCommand {
    @Override
    public ClientCommand select(String table) {
        return null;
    }

    @Override
    public ClientCommand select(String table, String condition, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand select(String table, String condition, String sort, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand Select(String sql, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Structure target) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Structure target, String seq) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Collection<Structure> targets) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Collection<Structure> targets, String seq) {
        return null;
    }

    @Override
    public ClientCommand insert(String table, Collection<Structure> targets, String seq, boolean isParallel) {
        return null;
    }

    @Override
    public ClientCommand update(String table, Structure target) {
        return null;
    }

    @Override
    public ClientCommand update(String table, Structure target, String condition, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand update(String table, Collection<Structure> targets) {
        return null;
    }

    @Override
    public ClientCommand delete(String table, Structure target) {
        return null;
    }

    @Override
    public ClientCommand delete(String table, Structure target, String condition, Object[] args) {
        return null;
    }

    @Override
    public ClientCommand delete(String table, Collection<Structure> targets) {
        return null;
    }

    @Override
    public ClientCommand Execute() {
        return null;
    }
}
