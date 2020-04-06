package pers.conan.easystorage.exception;

/**
 * 类：无法解析异常
 * @author Conan Jordan
 */
public class DisableToParseException extends Exception {

    static final long serialVersionUID = 1L;

    public DisableToParseException() {
        super("It is disable to be parsed to a SQL sentence.");
    }
}
