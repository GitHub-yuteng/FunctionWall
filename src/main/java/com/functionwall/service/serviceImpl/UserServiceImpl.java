package com.functionwall.service.serviceImpl;

import com.functionwall.constant.ConstantUserField;
import com.functionwall.dao.UserMapper;
import com.functionwall.pojo.model.User;
import com.functionwall.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
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

    @Autowired
    public JavaMailSenderImpl mailSender;

    protected JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

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
        user.setLevel("");
        user.setSex(1);
        user.setCreatedDate(new Date());
        user.setUsername(ConstantUserField.randomName[(int) (Math.random() * ConstantUserField.GetRandomNameLength + 1)]);
        String md5EncodePassword = new Md5Hash(password, "", 1024).toHex();
        user.setPassword(md5EncodePassword);
        getUserDao().save(user);
    }

    @Override
    @Async
    public void sendMail(String email, String userId, String realname) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("Function Wall Mail");
        mailMessage.setText("用户ID：[" + userId + "]，" + "用户姓名：[" + realname + "] ==> " + email);
        mailMessage.setTo("995689575@qq.com");
        mailMessage.setFrom("995689575@qq.com");

        mailSender.send(mailMessage);
        System.out.println("发送！");
    }

    /**
     * 根据用户ID更新用户网名
     */
    @Override
    public void updateUsernameById(String userId, String username) {
        getUserDao().updateUsernameById(userId,username);
    }
}



