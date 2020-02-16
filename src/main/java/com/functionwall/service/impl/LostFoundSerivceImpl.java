package com.functionwall.service.impl;

import com.functionwall.dao.LostFoundMapper;
import com.functionwall.pojo.model.Item;
import com.functionwall.service.LostFoundSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
