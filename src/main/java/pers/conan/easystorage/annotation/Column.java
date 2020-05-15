package pers.conan.easystorage.annotation;

import java.lang.annotation.*;

/**
 * 注解：字段
 *
 * @author Conan
 */
@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value();
}
