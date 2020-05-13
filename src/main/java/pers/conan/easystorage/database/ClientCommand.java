package pers.conan.easystorage.database;

import pers.conan.easystorage.annotation.Structure;

import java.util.Collection;

/**
 * 类：客户端命令
 */
public class ClientCommand extends BaseCommand {
    @Override
    public BaseCommand select(String table) {
        return null;
    }

    @Override
    public BaseCommand select(String table, String condition, Object[] args) {
        return null;
    }

    @Override
    public BaseCommand insert(String table, Structure target) {
        return null;
    }

    @Override
    public BaseCommand insert(String table, Structure target, String seq) {
        return null;
    }

    @Override
    public BaseCommand insert(String table, Collection<Structure> targets) {
        return null;
    }

    @Override
    public BaseCommand insert(String table, Collection<Structure> targets, String seq) {
        return null;
    }

    @Override
    public BaseCommand insert(String table, Collection<Structure> targets, String seq, boolean isParallel) {
        return null;
    }

    @Override
    public BaseCommand update(String table, Structure target) {
        return null;
    }

    @Override
    public BaseCommand update(String table, Structure target, String condition, Object[] args) {
        return null;
    }

    @Override
    public BaseCommand update(String table, Collection<Structure> targets) {
        return null;
    }

    @Override
    public BaseCommand delete(String table, Structure target) {
        return null;
    }

    @Override
    public BaseCommand delete(String table, Structure target, String condition, Object[] args) {
        return null;
    }

    @Override
    public BaseCommand delete(String table, Collection<Structure> targets) {
        return null;
    }

    @Override
    public BaseCommand Execute() {
        return null;
    }
}
