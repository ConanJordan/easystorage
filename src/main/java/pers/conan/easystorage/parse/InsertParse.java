package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;
import pers.conan.easystorage.util.ParseUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类：增加记录的解析器
 * @author Conan Jordan
 */
public class InsertParse extends BaseParse {

    /**
     * 表名
     * 新增数据的表
     */
    private String table;

    /**
     * 新增数据的模块集合
     */
    private Set<Module> modules = new HashSet<Module>();

    /**
     * 新增记录的列
     */
    private List<String> cols = new ArrayList<String>();

    /**
     * 新增记录的值
     */
    private List<String> values = new ArrayList<String>();

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public List<String> getCols() {
        return cols;
    }

    public void setCols(List<String> cols) {
        this.cols = cols;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    /**
     * 添加数据模块
     * @param module
     */
    public void add(Module module) {
        this.modules.add(module);
    }

    /**
     * 解析(生成SQL语句)
     *
     * @return
     */
    @Override
    public String parse() throws DisableToParseException {

        if (ParseUtil.isEmpty(this.table) || ParseUtil.isEmpty(this.modules)) {
            throw new DisableToParseException();
        }

        // 获取新增记录的列名和数据
        getInsertColAndVal(this.cols, this.values);

        StringBuilder sb = new StringBuilder("INSERT INTO ");

        sb.append(this.table);  // 表名

        sb.append(" (");

        for (int i = 0; i< this.cols.size(); i ++) {
            if (i == 0) {  // 第一列
                sb.append(this.cols.get(i));
            } else {
                sb.append(", " + this.cols.get(i));
            }
        }

        sb.append(") VALUES (");

        for (int i = 0; i< this.values.size(); i ++) {
            if (i == 0) {  // 第一列
                sb.append(this.values.get(i));
            } else {
                sb.append(", " + this.values.get(i));
            }
        }

        sb.append(")");

        return sb.toString();
    }

    /**
     * 获取新增记录的列名和数据
     * @param cols
     * @param vals
     */
    private void getInsertColAndVal(List<String> cols, List<String> vals) {
        for (Module module : this.modules) {
            cols.add(module.getName());

            switch (module.getType()) {
                case NUMBER:
                    vals.add(module.getValue().toString());
                    break;

                case TEXT :
                    vals.add("'" +
                            module.getValue().toString()
                    + "'");
                    break;

                case DATE :
                case OTHER:
                    // TODO
                    break;

                default :
                    vals.add(module.getValue().toString());
                    break;
            }
        }
    }




}
