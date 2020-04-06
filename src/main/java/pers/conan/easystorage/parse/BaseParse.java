package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;

/**
 * 类：基础解析器
 * @author Conan Jordan
 */
public abstract class BaseParse implements Parsable {

    /**
     * 解析后生成的SQL语句
     */
    protected String sql;

    /**
     * 解析(生成SQL语句)
     *
     * @return
     */
    public abstract String parse() throws DisableToParseException;
}
