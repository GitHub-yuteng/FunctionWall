package com.functionwall.service;

import com.functionwall.pojo.model.Topic;

import java.util.List;

/**
 * @author Yu
 */
public interface TopicService {

    void save(String title, String category, String content,String userId);

    List<Topic> queryListForLoveWallTopic();

    List<Topic> queryListForComplaintWallTopic();
}
