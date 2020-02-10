package com.functionwall.dao;

import com.functionwall.pojo.model.Topic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoveWallMapper {
    String TABLE_NAME = "topic_lovewall";
    String INSERT_FIELDS = "title,category,content,image,created_date";
    String SELECT_FIELDS = "id,title,category,content,image,created_date";

    void save(Topic topic);
}