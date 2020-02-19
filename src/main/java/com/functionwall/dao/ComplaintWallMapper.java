package com.functionwall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.functionwall.pojo.model.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComplaintWallMapper extends BaseMapper<Topic> {
    String TABLE_NAME = "topic_complaintwall";
    String INSERT_FIELDS = "title,username,link,content,imageUrl,createdDate,idUser";
    String SELECT_FIELDS = "id,title,username,link,content,imageUrl,createdDate,idUser";

    void save(Topic topic);

    List<Topic> queryListForComplaintWallTopics();

    List<Topic> listComplaintTopicByUserId(@Param("userId") String userId);

    void deleteTopicByComplaintTopicIdForUser(@Param("id") Integer id);
}