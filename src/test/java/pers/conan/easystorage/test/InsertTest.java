package pers.conan.easystorage.test;

import pers.conan.easystorage.parse.DataType;
import pers.conan.easystorage.parse.InsertParse;
import pers.conan.easystorage.parse.Module;
import pers.conan.easystorage.exception.DisableToParseException;

public class InsertTest {

    public static void main(String[] args) throws DisableToParseException {
        InsertParse insert = new InsertParse();
        insert.setTable("dba.student");
        /*insert.add(new Module("id", 1, DataType.NUMBER, "ID"));
        insert.add(new Module("name", "Java", DataType.TEXT, "姓名"));*/
        System.out.println(insert.parse());
    }

}
