package com.functionwall.service.impl;

import com.functionwall.constant.ConstantField;
import com.functionwall.dao.ComplaintWallMapper;
import com.functionwall.dao.LoveWallMapper;
import com.functionwall.dao.UserMapper;
import com.functionwall.exception.FunctionWallErrorCode;
import com.functionwall.exception.FunctionWallRuntimeException;
import com.functionwall.pojo.model.Topic;
import com.functionwall.service.TopicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Yu
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private LoveWallMapper loveWallMapper;

    @Autowired
    private ComplaintWallMapper complaintWallMapper;

    protected LoveWallMapper getLoveWallMapper() {
        return loveWallMapper;
    }

    protected ComplaintWallMapper getComplaintWallMapper() {
        return complaintWallMapper;
    }

    @Override
    public void save(String title, String category, String username, String link, String imageUrl, String content,
                     String userId) {

        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setUsername(username);
        topic.setLink(link);
        topic.setImageUrl(imageUrl);
        topic.setContent(content);
        topic.setCreatedDate(new Date());
        topic.setIdUser(Long.parseLong(userId));

        if (category.equals(ConstantField.LOVEWALL)) {
            getLoveWallMapper().save(topic);
        } else if (category.equals(ConstantField.COMPLAINTWALL)) {
            getComplaintWallMapper().save(topic);
        } else {
            throw new FunctionWallRuntimeException(FunctionWallErrorCode.CATEGORY_IS_NULL);
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
        PageInfo<Topic> page = new PageInfo<>(complaintWallTopicList);
        return page;
    }

    @Override
    public void deleteTopicByLoveTopicIdForUser(Integer id) {
        getLoveWallMapper().deleteTopicByLoveTopicIdForUser(id);
    }

    @Override
    public void deleteTopicByComplaintTopicIdForUser(Integer id) {
        getComplaintWallMapper().deleteTopicByComplaintTopicIdForUser(id);
    }
}
