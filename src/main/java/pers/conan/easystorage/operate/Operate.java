package pers.conan.easystorage.operate;

/**
 * 类：操作
 * CRUD
 *
 * @author Conan
 */
public interface Operate {
    
    void prepare() throws Exception;
    
    void operate();
}
