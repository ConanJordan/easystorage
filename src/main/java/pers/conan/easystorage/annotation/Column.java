package pers.conan.easystorage.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)  // 给属性做注解
@Retention(RetentionPolicy.RUNTIME)  // 注解保留到运行环境里
public @interface Column {
    String name() default "";  // 字段名
    String alias() default "";  // 字段别名
}
