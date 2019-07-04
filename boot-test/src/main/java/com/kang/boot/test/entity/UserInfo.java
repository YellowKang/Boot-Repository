package com.kang.boot.test.entity;

import com.kang.boot.test.annotation.TableName;
import lombok.Data;

@Data
@TableName(tableName = "t_user_info")
public class UserInfo {

    private String id;
    private String name;

}
