package com.functionwall.dao;

import com.functionwall.pojo.mould.Topic;
import com.functionwall.pojo.mould.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoveWallMapper {
    String TABLE_NAME = "topic_lovewall";
    String INSERT_FIELDS = "title,category,content,image,created_date";
    String SELECT_FIELDS = "id,title,category,content,image,created_date";

    void save(Topic topic);
}