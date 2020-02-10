package com.functionwall.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id; //用户ID
    private String username; //用户名
    private String account; //用户账号
    private String password;//用户密码
    private String realname; //真实姓名
    private String level;//用户级别
    private int sex;//用户级别  1 男  0 女  默认 -1
    private Date createdDate;//用户的注册时间
}
