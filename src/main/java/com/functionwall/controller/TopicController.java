package com.functionwall.controller;

import com.functionwall.constant.ConstantField;
import com.functionwall.pojo.model.Topic;
import com.functionwall.service.QiniuService;
import com.functionwall.service.TopicService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Yu
 */
@Api(tags = "功能墙")
@Controller
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    protected TopicService getTopicService() {
        return topicService;
    }

    @Autowired
    private QiniuService qiniuService;

    protected QiniuService getQiniuService() {
        return qiniuService;
    }

    /**
     * 根据类型添加帖子
     *
     * @param title
     * @param category
     * @param content
     * @param userId
     * @param request
     * @return
     */
    @PostMapping(value = "/content")
    public View saveTopic(@RequestParam String title,
                          @RequestParam String category,
                          @RequestParam String realnameUser,
                          @RequestParam(required = false, defaultValue = "") String link,
                          @RequestParam(required = false, defaultValue = "") MultipartFile image,
                          @RequestParam String content,
                          @RequestParam String userId,
                          HttpServletRequest request) {
        String contextPath = request.getContextPath();

        try {
            String imageUrl = getQiniuService().saveImage(image);
            getTopicService().save(title, category, realnameUser, link,imageUrl,content, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ConstantField.LOVEWALL.equals(category)) {
            return new RedirectView(contextPath + "/topic/love-wall");
        } else if (ConstantField.COMPLAINTWALL.equals(category)) {
            return new RedirectView(contextPath + "/topic/complaint-wall");
        } else {
            return new RedirectView(contextPath + "/404");
        }
    }


    /**
     * 分页获取表白墙 Topic
     *
     * @param pageNo
     * @param pageSize
     * @param model
     */
    @GetMapping(value = "/love-wall")
    public String queryListForLoveWallTopic(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                            Model model) {
        PageInfo<Topic> pageInfo = getTopicService().queryListForLoveWallTopic(pageNo, pageSize);
        model.addAttribute("loveInfo", pageInfo);
        return "love-wall";
    }

    /**
     * 分页获取吐槽墙 Topic
     *
     * @param pageNo
     * @param pageSize
     * @param model
     */
    @GetMapping(value = "/complaint-wall")
    public String queryListForComplaintWallTopic(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "9") Integer pageSize,
                                                 Model model) {
        PageInfo<Topic> pageInfo = getTopicService().queryListForComplaintWallTopic(pageNo, pageSize);
        model.addAttribute("complaintInfo", pageInfo);
        return "complaint-wall";
    }


    @GetMapping(value = "/post")
    public String topicPost() {
        return "post-topic";
    }
}
