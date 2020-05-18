package pers.conan.easystorage.operate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.stream.Stream;

import pers.conan.easystorage.annotation.Structure;
import pers.conan.easystorage.database.ClientCommand;

/**
 * 抽象类：预编译
 * 
 * @author Conan
 */
public abstract class PreCompile {
    /**
     * 基于SQL
     * @throws Exception
     */
    protected abstract void bySql() throws Exception;
    
    /**
     * 基于条件
     * @throws Exception
     */
    protected abstract void byCondition() throws Exception;
    
    /**
     * 基于目标对象
     * @throws Exception
     */
    protected abstract void byTarget() throws Exception;
    
    /**
     * 基于目标对象集合
     * @throws Exception
     */
    protected abstract void byTargets() throws Exception;
    
    protected Connection connection;
    protected PreparedStatement prst;
    protected ResultSet rs;
    protected String table;
    protected String SQL;
    protected String condition;
    protected Object[] args;
    protected String sort;
    protected Class<? extends Structure> structure;
    protected Structure target;
    protected Collection<? extends Structure> targets;
    protected PreparedStatementType psType;
    protected int resultCount = 0;
    protected ClientCommand command;
    protected Stream<? extends Structure> resultStream;
    
    public void setPsType(PreparedStatementType psType) {
        this.psType = psType;
    }

}
