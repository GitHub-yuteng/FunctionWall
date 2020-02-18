package com.functionwall.service;

import com.functionwall.pojo.model.Item;
import com.functionwall.pojo.model.Topic;
import com.functionwall.pojo.model.User;

import java.util.List;

/**
 * @author Yu
 */

public interface UserService {

    User getUserByUsername(String username);

    User getUserByAccount(String account);

    User getUserById(String userId);

    void save(String realname, String account, String password);

    void updateUsernameById(String userId, String username);

    Boolean updatePasswordById(String userId, String oldPassword, String newPassword);

    void sendMail(String email, String userId, String realname);

    List<Topic> listLoveTopicByUserId(String userId);

    List<Topic> listComplaintTopicByUserId(String userId);

    List<Item> listLostFoundItemByUserId(String userId);
}
