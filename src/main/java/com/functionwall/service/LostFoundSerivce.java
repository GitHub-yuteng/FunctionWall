package com.functionwall.service;

import com.functionwall.pojo.model.Item;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Yu
 */
public interface LostFoundSerivce {

    void save(String realnameUser, String link, String type, String category, String content, String imageUrl,
              String userId);

    List<Item> queryListForAllItem(Integer pageNo, Integer pageSize);
}
