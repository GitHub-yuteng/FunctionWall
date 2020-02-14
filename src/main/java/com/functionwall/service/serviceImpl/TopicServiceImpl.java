package com.functionwall.service.serviceImpl;

import com.functionwall.constant.ConstantField;
import com.functionwall.dao.ComplaintWallMapper;
import com.functionwall.dao.LoveWallMapper;
import com.functionwall.dao.UserMapper;
import com.functionwall.pojo.model.Topic;
import com.functionwall.service.TopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Yu
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoveWallMapper loveWallMapper;

    @Autowired
    private ComplaintWallMapper complaintWallMapper;

    protected UserMapper getUserMapper() {
        return userMapper;
    }

    protected LoveWallMapper getLoveWallMapper() {
        return loveWallMapper;
    }

    protected ComplaintWallMapper getComplaintWallMapper() {
        return complaintWallMapper;
    }

    @Override
    public void save(String title, String category, String content, String userId) {

        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setCategory(category);
        topic.setContent(content);
        topic.setCreatedDate(new Date());
        topic.setIdUser(Long.parseLong(userId));

        if (category.equals(ConstantField.LOVEWALL)) {
            getLoveWallMapper().save(topic);
        }
        if (category.equals(ConstantField.COMPLAINTWALL)) {
            getComplaintWallMapper().save(topic);
        }
    }

    /**
     * 查询表白墙
     *
     * @return
     */
    @Override
    public PageInfo<Topic> queryListForLoveWallTopic(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Topic> loveWallTopicList = getLoveWallMapper().queryListForLoveWallTopics();
        PageInfo<Topic> page = new PageInfo<>(loveWallTopicList);
        return page;
    }

    /**
     * 查询吐槽墙
     *
     * @return
     */
    @Override
    public PageInfo queryListForComplaintWallTopic(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Topic> complaintWallTopicList = getComplaintWallMapper().queryListForComplaintWallTopics();
        PageInfo<Topic> page = new PageInfo<>(complaintWallTopicList, 5);
        return page;
    }
}
