package com.functionwall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.functionwall.pojo.model.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Yu
 */
@Mapper
public interface LostFoundMapper extends BaseMapper<Item> {

    List<Item> listLostFoundItemByUserId(String userId);
}
