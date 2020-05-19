package pers.conan.easystorage.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 注解：自动递增
 * 
 * @author Conan
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface AutoIncrement {

}
