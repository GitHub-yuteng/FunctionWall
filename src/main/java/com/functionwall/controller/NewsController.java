package com.functionwall.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Api(tags = "新闻相关")
@Controller
public class NewsController {

    @GetMapping(value = "/news-list")
    public String newsList() {
        return "news-list";
    }

    @GetMapping(value = "/news-tiles")
    public String newsTiles() {
        return "news-tiles";
    }

    @GetMapping(value = "/single-post")
    public String singlePost() {
        return "single-post";
    }

    @GetMapping(value = "/single-post-video")
    public String singlePostVideo() {
        return "single-post-video";
    }

    @GetMapping(value = "single-post-no-sidebar")
    public String singlePostNoSidebar() {
        return "single-post-no-sidebar";
    }
}
