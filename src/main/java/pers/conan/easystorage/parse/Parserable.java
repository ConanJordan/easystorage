package pers.conan.easystorage.parse;

import pers.conan.easystorage.parse.exception.DisableToParseException;

/**
 * 接口：可解析
 * @author Conan Jordan
 */
public interface Parserable {

    /**
     * 解析(生成SQL语句)
     * @return
     */
    String parse() throws DisableToParseException;

}
