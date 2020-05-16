package pers.conan.easystorage.operate;

/**
 * 枚举：预编译类型
 * 
 * @author Conan
 */
public enum PreparedStatementType {
    /**
     * SQL语句
     */
    SQL,
    
    /**
     * 条件
     */
    CONDITION,
    
    /**
     * 目标对象
     */
    TARGET,
    
    /**
     * 目标对象集合
     */
    TARGETS
}
