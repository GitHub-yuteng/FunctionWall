package com.functionwall.dao;

import com.functionwall.pojo.model.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplaintWallMapper {
    String TABLE_NAME = "topic_complaintwall";
    String INSERT_FIELDS = "title,category,content,image,created_date";
    String SELECT_FIELDS = "title,category,content,image,created_date";

    void save(Topic topic);

    List<Topic> queryListForComplaintWallTopics();
}