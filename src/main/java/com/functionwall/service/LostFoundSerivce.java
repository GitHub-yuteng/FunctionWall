package com.functionwall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.functionwall.pojo.model.Item;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Yu
 */
public interface LostFoundSerivce {

    void save(String realnameUser, String link, Integer type, String category, String content, String imageUrl,
              String userId);

    Page<Item> queryListForAllItem(Integer type, Integer pageNo, Integer pageSize);
}
