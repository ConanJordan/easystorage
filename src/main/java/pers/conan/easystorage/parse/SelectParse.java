package pers.conan.easystorage.parse;

import pers.conan.easystorage.exception.DisableToParseException;
import pers.conan.easystorage.util.ParseUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectParse extends BaseParse {

    /**
     * 查询表
     */
    private String table;

    /**
     * 查询项
     */
    private List<Module> selections = new ArrayList<Module>();

    /**
     * 查询条件
     */
    private Condition condition;

    /**
     * 查询结果排序
     */
    private Sort sort;

    /**
     * 解析(生成SQL语句)
     *
     * @return
     */
    @Override
    public String parse() throws DisableToParseException {

        if (ParseUtil.isEmpty(this.table)) {  // 没有要查询的表
            throw new DisableToParseException();
        }

        if (ParseUtil.isEmpty(this.selections)) {  // 没有要查询的项目
            throw new DisableToParseException();
        }

        StringBuilder sb = new StringBuilder("SELECT ");

        sb.append(ParseUtil.editSelections(this.selections, this.table));  // 编辑查询项

        sb.append(" FROM " + this.table);  // 查询的表

        if (ParseUtil.isEmpty(this.condition) == false) {  // 有查询条件
            sb.append(" WHERE ");
            // TODO
        }

        if (ParseUtil.isEmpty(this.sort)) {  // 有排序条件
            sb.append(" SORTED BY ");
            // TODO
        }



        return null;
    }
}
