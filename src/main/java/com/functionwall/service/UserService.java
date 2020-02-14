package com.functionwall.service;

import com.functionwall.pojo.model.User;

/**
 * @author Yu
 */

public interface UserService {

    User getUserByUsername(String username);

    User getUserByAccount(String account);

    User getUserById(String userId);

    void save(String realname, String account, String password);

    void sendMail(String email, String userId, String realname);

    void updateUsernameById(String userId, String username);

    Boolean updatePasswordById(String userId, String oldPassword, String newPassword);
}
