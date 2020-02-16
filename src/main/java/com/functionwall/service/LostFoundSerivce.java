package com.functionwall.service;

/**
 * @author Yu
 */
public interface LostFoundSerivce {

    void save(String realnameUser, String link, String type, String category, String content, String imageUrl,
              String userId);
}
