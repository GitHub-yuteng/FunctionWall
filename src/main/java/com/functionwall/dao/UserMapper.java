package com.functionwall.dao;

import com.functionwall.pojo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    String TABLE_NAME = "user";
    String INSERT_FIELDS = "username,account,password,realname,level,sex,created_date";
    String SELECT_FIELDS = "id,username,account,password,realname,level,sex,created_date";

    /**
     * 通过帐号密码验证用户
     * @param account
     * @param password
     * @return
     */
    User getUserInfoByAccountAndPassword(@Param("account") String account, @Param("password") String password);


    /**
     * 注册一个用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 根据用户名获得相应用户
     *
     * @param username
     * @return User
     */
    User getUserByUsername(@Param("username") String username);


    /**
     * 根据用户帐号获得相应用户
     * @param account
     * @return
     */
    User getUserByAccount(@Param("account") String account);

    /**
     * 根据用户id获得相应用户
     *
     * @param id
     * @return User
     */
    User getUserById(@Param("id") Long id);

}