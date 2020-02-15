package com.functionwall.dao;

import com.functionwall.pojo.model.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoveWallMapper {
    String TABLE_NAME = "topic_lovewall";
    String INSERT_FIELDS = "title,realnameUser,link,content,imageUrl,createdDate,idUser";
    String SELECT_FIELDS = "id,title,realnameUser,link,content,imageUrl,createdDate,idUser";

    void save(Topic topic);

    List<Topic> queryListForLoveWallTopics();
}