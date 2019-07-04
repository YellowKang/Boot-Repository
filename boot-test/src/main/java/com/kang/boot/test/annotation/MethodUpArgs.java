package com.kang.boot.test.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MethodUpArgs {

    String[] argsName() default "";
    String[] argsValue() default "";

}
