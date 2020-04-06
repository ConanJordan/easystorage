package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;

/**
 * 接口：可解析
 * @author Conan Jordan
 */
public interface Parsable {

    /**
     * 解析(生成SQL语句)
     * @return
     */
    String parse() throws DisableToParseException;

}
