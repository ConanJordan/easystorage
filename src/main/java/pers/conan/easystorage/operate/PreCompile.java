package pers.conan.easystorage.operate;

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
}
