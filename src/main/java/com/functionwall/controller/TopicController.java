package com.functionwall.controller;

import com.functionwall.constant.ConstantField;
import com.functionwall.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yu
 */
@Controller
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    public TopicService getTopicService() {
        return topicService;
    }

    /**
     * 根据类型添加帖子
     * @param title
     * @param category
     * @param content
     * @param userId
     * @param request
     * @return
     */
    @PostMapping(value = "/add")
    public View addTopic(@RequestParam String title,
                         @RequestParam String category,
                         @RequestParam String content,
                         @RequestParam String userId,
                         HttpServletRequest request) {
        String contextPath = request.getContextPath();
        getTopicService().save(title, category, content, userId);

        if (ConstantField.LOVEWALL.equals(category)) {
            return new RedirectView(contextPath + "/topic/love-wall");
        } else if (ConstantField.COMPLAINTWALL.equals(category)) {
            return new RedirectView(contextPath + "/topic/complaint-wall");
        } else {
            return new RedirectView(contextPath + "/404");
        }
    }

//    @GetMapping(value = "/queryList")
//    public APIResponse<Topic> queryListPageForTopic(PageBean pageBean){
//        getTopicService().queryListPageForTopic();
//    }

    @GetMapping(value = "/post")
    public String topic() {
        return "post-topic";
    }


    @GetMapping(value = "/complaint-wall")
    public String complaintWall() {
        return "complaint-wall";
    }

    @GetMapping(value = "/love-wall")
    public String loveWall() {
        return "love-wall";
    }
}
