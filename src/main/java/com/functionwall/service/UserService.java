package com.functionwall.service;

import com.functionwall.pojo.model.User;

/**
 * @author Yu
 */

public interface UserService {

    User getUserByUsername(String username);

    User getUserByAccount(String account);

    void save(String realname, String account, String password);

}
