package pers.conan.easystorage.exception;

/**
 * 异常类：字段同时存在序列号和自动递增，造成冲突
 * 
 * @author Conan
 */
public class SequenceIncrementCoflictException extends RuntimeException {

    private static final long serialVersionUID = -7266677426441726543L;

    public SequenceIncrementCoflictException(String column) {
        super("The column " + column + " has both a sequence and an autoincrement feature, which leads a conflic when attempting to insert records into a table.");    
    }

}
