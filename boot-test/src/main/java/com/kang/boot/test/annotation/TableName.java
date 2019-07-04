package com.kang.boot.test.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TableName {

    String tableName() default "请设置表名";

}
