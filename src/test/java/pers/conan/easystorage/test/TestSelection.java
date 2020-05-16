package pers.conan.easystorage.test;

import pers.conan.easystorage.operate.Selection;

public class TestSelection {
    public static void main(String[] args) {
        String table = "my_table";
        String column = "id";
        String alias = "";
        Selection<Integer, Object> selection = new Selection<Integer, Object>(
                table,
                column,
                alias,
                item -> (Integer)item + (Integer)item
                );

        selection.setBefore(123);
        selection.edit();
        System.out.println("After edited:" + selection.getAfter());
    }
}
