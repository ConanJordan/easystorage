package pers.conan.easystorage.test;

@Table(name = "student", alias = "学生")  // 表名:student 别名：s
public class Student {

    @Column(name = "no", alias = "学号")
    private int no;

    @Column(name = "name", alias = "姓名")
    private String name;
}
