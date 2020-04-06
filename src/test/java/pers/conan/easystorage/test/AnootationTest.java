package pers.conan.easystorage.test;

import java.lang.annotation.Annotation;

public class AnootationTest {

    public static void main(String[] args) {
        tableInfo();
    }

    /**
     * 获取表信息
     */
    public static void tableInfo() {

        if (Student.class.isAnnotationPresent(Table.class)  == false) {
            System.out.println("Student类没有被Table注解。");
        }
        System.out.println("Student类被Table注解了。");

        Class<Student> s = Student.class;  // 获取Student类对象

        Annotation[] annotations = s.getAnnotations();  // 获取类注解

        for (Annotation annotation : annotations) {
            if (annotation instanceof Table) {  // 获取到的类注解是Table类型的话
                System.out.println("表名:" + ((Table) annotation).name());
                System.out.println("表别名:" + ((Table) annotation).alias());
            }
        }

    }
}
