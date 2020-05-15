package pers.conan.easystorage.operate;

/**
 * 类：操作
 * CRUD
 *
 * @author Conan
 */
public interface Operate {
    
    /**
     * 准备SQL语句
     * @throws Exception
     */
    void prepare() throws Exception;
    
    /**
     * 执行SQL操作
     */
    void operate();
}
