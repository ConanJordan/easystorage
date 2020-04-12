package pers.conan.easystorage.test;

import pers.conan.easystorage.exception.DisableToParseException;
import pers.conan.easystorage.parse.*;
import pers.conan.easystorage.parse.Module;

import java.util.ArrayList;
import java.util.List;

public class SelectTest {
    public static void main(String[] args) throws DisableToParseException {
        SelectParse select = new SelectParse();

        select.setTable("student");

        List<Module> selections = new ArrayList<Module>();
        /*selections.add(new Module("id","ID"));
        selections.add(new Module("name","姓名"));
        selections.add(new Module("gender","性别"));
        select.setSelections(selections);  // 设置查询项

        Condition condition = new Condition("ID",0, ConditionType.NOT_EQUAL);
        condition.addAnd(new Condition("性别", "Female", ConditionType.EQUAL));
        select.setCondition(condition);  // 设置条件

        Sort sort = new Sort();
        sort.addSort(null, true, "姓名");
        select.setSort(sort);  // 设置排序条件*/

        System.out.println(select.parse());
    }
}
