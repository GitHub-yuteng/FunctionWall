package com.functionwall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.functionwall.dao.LostFoundMapper;
import com.functionwall.pojo.model.Item;
import com.functionwall.service.LostFoundSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public void save(String realnameUser, String link, String type, String category, String content, String imageUrl, String userId) {

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
    public List<Item> queryListForAllItem(Integer pageNo, Integer pageSize) {
        IPage<Item> itemIPage = getLostFoundMapper().selectPage(new Page<>(pageNo, pageSize), null);
        return itemIPage.getRecords();
    }
}
