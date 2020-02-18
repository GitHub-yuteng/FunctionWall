package com.functionwall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.functionwall.constant.ConstantUserField;
import com.functionwall.dao.ComplaintWallMapper;
import com.functionwall.dao.LostFoundMapper;
import com.functionwall.dao.LoveWallMapper;
import com.functionwall.dao.UserMapper;
import com.functionwall.pojo.model.Item;
import com.functionwall.pojo.model.Topic;
import com.functionwall.pojo.model.User;
import com.functionwall.service.LostFoundSerivce;
import com.functionwall.service.TopicService;
import com.functionwall.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Yu
 */
@Service
public class UserServiceImpl implements UserService {

    private static final transient Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    private LoveWallMapper loveWallMapper;

    @Autowired
    private ComplaintWallMapper complaintWallMapper;

    @Autowired
    LostFoundMapper lostFoundMapper;

    protected LostFoundMapper getLostFoundMapper() {
        return lostFoundMapper;
    }

    protected LoveWallMapper getLoveWallMapper() {
        return loveWallMapper;
    }

    protected ComplaintWallMapper getComplaintWallMapper() {
        return complaintWallMapper;
    }


    protected JavaMailSender getMailSender() {
        return javaMailSender;
    }

    protected UserMapper getUserMapper() {
        return userMapper;
    }

    /**
     * 根据用户名获得相应用户
     *
     * @param username
     * @return User
     */
    @Override
    public User getUserByUsername(String username) {
        User user = getUserMapper().getUserByUsername(username);
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
        User user = getUserMapper().getUserByAccount(account);
        return user;
    }

    /**
     * 根据用户ID获得相应用户
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(String userId) {
        User user = getUserMapper().getUserById(userId);
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
        getUserMapper().save(user);
    }

    /**
     * 发送邮件
     *
     * @param email
     * @param userId
     * @param realname
     */
    @Override
    @Async
    public void sendMail(String email, String userId, String realname) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("FunctionWall Mail");
        mailMessage.setText("用户ID：[" + userId + "]，" + "用户姓名：[" + realname + "] ==> " + email);
        mailMessage.setTo("995689575@qq.com");
        mailMessage.setFrom("995689575@qq.com");

        getMailSender().send(mailMessage);
        System.out.println("发送！");
    }

    /**
     * 根据用户ID获取所发表的全部 LoveTopic
     *
     * @param userId
     * @return
     */
    @Override
    public List<Topic> listLoveTopicByUserId(String userId) {
        List<Topic> loveTopicList = getLoveWallMapper().listLoveTopicByUserId(userId);
        return loveTopicList;
    }

    /**
     * 根据用户ID获取所发表的全部 ComplaintTopic
     *
     * @param userId
     * @return
     */
    @Override
    public List<Topic> listComplaintTopicByUserId(String userId) {
        List<Topic> complaintTopicList = getComplaintWallMapper().listComplaintTopicByUserId(userId);
        return complaintTopicList;
    }

    /**
     * 根据用户ID获取所发表的全部 Item
     *
     * @param userId
     * @return
     */
    @Override
    public List<Item> listLostFoundItemByUserId(String userId) {
        List<Item> itemList = getLostFoundMapper().listLostFoundItemByUserId(userId);
        return itemList;
    }

    /**
     * 根据用户ID更新用户网名
     */
    @Override
    public void updateUsernameById(String userId, String username) {
        getUserMapper().updateUsernameById(userId, username);
    }

    /**
     * 根据用户ID更新密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public Boolean updatePasswordById(String userId, String oldPassword, String newPassword) {
        User user = getUserMapper().getUserById(userId);

        String oldPasswordMD5 = new Md5Hash(oldPassword, "", 1024).toHex();
        if (StringUtils.equals(user.getPassword(), oldPasswordMD5)) {
            String newPasswordMD5 = new Md5Hash(newPassword, "", 1024).toHex();
            getUserMapper().updatePasswordById(userId, newPasswordMD5);
            return true;
        } else {
            return false;
        }
    }
}



