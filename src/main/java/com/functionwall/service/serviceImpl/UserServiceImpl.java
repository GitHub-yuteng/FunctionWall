package com.functionwall.service.serviceImpl;

import com.functionwall.constant.ConstantUserField;
import com.functionwall.dao.UserMapper;
import com.functionwall.pojo.mould.User;
import com.functionwall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Yu
 */
@Service
public class UserServiceImpl implements UserService {

    private static final transient Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userDao;

    protected UserMapper getUserDao() {
        return userDao;
    }

    /**
     * 根据用户名获得相应用户
     *
     * @param username
     * @return User
     */
    @Override
    public User getUserByUsername(String username) {
        User user = getUserDao().getUserByUsername(username);
        return user;
    }

    /**
     * 根据用户帐号获得相应用户
     *
     * @param account
     * @return
     */
    @Override
    public User getUserByAccount(String account) {
        User user = getUserDao().getUserByAccount(account);
        return user;
    }

    /**
     * 注册一个用户
     */
    @Override
    public void save(String realname, String account, String password) {
        User user = new User();
        user.setRealname(realname);
        user.setAccount(account);
        user.setPassword(password);
        user.setLevel("");
        user.setSex(1);
        user.setCreatedDate(new Date());
        user.setUsername(ConstantUserField.randomName[(int) (Math.random() * ConstantUserField.GetRandomNameLength + 1)]);
        getUserDao().save(user);
    }
}



