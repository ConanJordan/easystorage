package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;

import java.util.ArrayList;
import java.util.List;

public class UpdateParse extends BaseParse {

    /**
     * 更新表的名称
     */
    private String table;

    /**
     * 更新数据模块集合
     */
    private List<BaseModule> updates = new ArrayList<BaseModule>();

    /**
     * 条件数据模块集合
     */
    private List<ConditionModule> conModules = new ArrayList<ConditionModule>();

    /**
     * 构造方法
     * @param table
     * @param updates
     * @param conModules
     */
    public UpdateParse(String table, List<BaseModule> updates, List<ConditionModule> conModules) {
        this.table = table;
        this.updates = updates;
        this.conModules = conModules;
    }

    /**
     * 构造方法
     */
    public UpdateParse() {
    }

    /**
     * 解析(生成SQL语句)
     *
     * @return
     */
    @Override
    public String parse() throws DisableToParseException {
        return null;
    }
}
