package com.site.blog.my.core.entity;

import lombok.Data;

/**
 * @author feixing
 * @date 2023/6/20 22:42
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private String phone;
    private String status;
}
