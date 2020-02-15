package com.functionwall.service;

import com.functionwall.pojo.model.Topic;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Yu
 */
public interface TopicService {

    void save(String title, String category, String realnameUser, String link, String content, String userId);

    PageInfo<Topic> queryListForLoveWallTopic(Integer pageNo, Integer pageSize);

    PageInfo<Topic> queryListForComplaintWallTopic(Integer pageNo, Integer pageSize);
}
