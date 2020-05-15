package pers.conan.easystorage.test;

import pers.conan.easystorage.operate.Selection;

import java.util.function.Function;

public class TestSelection {
    public static void main(String[] args) {
        String table = "my_table";
        String column = "id";
        String alias = "";
        Function<Integer,Integer> edit = item -> item * item;

        Selection selection = new Selection(
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
