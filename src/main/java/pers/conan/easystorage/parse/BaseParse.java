package pers.conan.easystorage.parse;

/**
 * 类：基础解析
 * @author Conan Jordan
 */
public abstract class BaseParse implements Parserable {

    /**
     * 解析后生成的SQL语句
     */
    protected String sql;

    /**
     * 数据库概要
     */
    protected String schema;


    /**
     * 解析(生成SQL语句)
     *
     * @return
     */
    public abstract String parse();
}
