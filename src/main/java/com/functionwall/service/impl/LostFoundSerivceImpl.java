package com.functionwall.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.functionwall.dao.LostFoundMapper;
import com.functionwall.pojo.model.Item;
import com.functionwall.service.LostFoundSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Date;

/**
 * @author Yu
 */
@Service
public class LostFoundSerivceImpl implements LostFoundSerivce {


    @Autowired
    private LostFoundMapper lostFoundMapper;

    protected LostFoundMapper getLostFoundMapper() {
        return lostFoundMapper;
    }

    /**
     * 保存丢失的物品Item
     *
     * @param type     丢/捡
     * @param category 物品分类
     */
    @Override
    public void save(String realnameUser, String link, Integer type, String category, String content, String imageUrl, String userId) {

        Item item = new Item();
        item.setRealnameUser(realnameUser);
        item.setLink(link);
        item.setType(type);
        item.setCategory(category);
        item.setContent(content);
        item.setImageUrl(imageUrl);
        item.setCreatedDate(new Date());
        item.setIdUser(Long.parseLong(userId));
        getLostFoundMapper().insert(item);
    }

    /**
     * 分页获取全部Item
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Item> queryListForAllItem(Integer type, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<Item> eq = new QueryWrapper<Item>().lambda();
        if (type == 1 || type == 2) {
            eq.eq(Item::getType, type);
        }
        eq.orderByDesc(Item::getCreatedDate);
        Page<Item> itemIPage = (Page<Item>) getLostFoundMapper().selectPage(new Page<>(pageNo, pageSize), eq);
        return itemIPage;
    }
}
