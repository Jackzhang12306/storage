package cn.com.ecict.util;

import java.lang.annotation.*;

/**
 * 在需要验证权限的Controller上添加注解
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
    boolean validate() default true;
}
