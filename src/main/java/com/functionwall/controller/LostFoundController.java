package com.functionwall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.functionwall.constant.ConstantField;
import com.functionwall.pojo.model.Item;
import com.functionwall.pojo.model.Topic;
import com.functionwall.service.LostFoundSerivce;
import com.functionwall.service.QiniuService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author Yu
 */
@Api(tags = "失物认领")
@Controller
@RequestMapping(value = "/lost-found")
public class LostFoundController {


    @Autowired
    private LostFoundSerivce lostFoundSerivce;

    protected LostFoundSerivce getLostFoundSerivce() {
        return lostFoundSerivce;
    }

    @Autowired
    private QiniuService qiniuService;

    protected QiniuService getQiniuService() {
        return qiniuService;
    }


    /**
     * @param realnameUser
     * @param link
     * @param type
     * @param category
     * @param content
     * @param image
     * @param userId
     * @param request
     * @return
     */
    @PostMapping(value = "/content")
    public View saveTopic(@RequestParam String realnameUser,
                          @RequestParam(required = false, defaultValue = "") String link,
                          @RequestParam Integer type,
                          @RequestParam String category,
                          @RequestParam String content,
                          @RequestParam(required = false, defaultValue = "") MultipartFile image,
                          @RequestParam String userId,
                          HttpServletRequest request) {
        String contextPath = request.getContextPath();

        try {
            String imageUrl = getQiniuService().saveImage(image);
            getLostFoundSerivce().save(realnameUser, link, type, category, content, imageUrl, userId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new RedirectView(contextPath + "/lost-found/item/1");
    }


    /**
     * 分页获取对应 Item
     *
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping(value = {"/item/{type:1|2}"})
    public String queryListForAllItem(@PathVariable(required = false) Integer type,
                                      @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "9") Integer pageSize,
                                      Model model) {
        Page<Item> itemInfo = getLostFoundSerivce().queryListForAllItem(type, pageNo, pageSize);
        model.addAttribute("itemInfo", itemInfo);
        model.addAttribute("currPage", itemInfo.getCurrent());
        model.addAttribute("pages", itemInfo.getPages());
        model.addAttribute("type", type);
        return "lost-found";
    }


    @GetMapping(value = "/item-fullwidth")
    public String lostFoundFullwidth() {
        return "lost-found-fullwidth";
    }

    @GetMapping(value = "/post")
    public String topicPost() {
        return "post-item";
    }

    @GetMapping(value = "/single-portfolio-video")
    public String singlePortfolioVideo() {
        return "single-portfolio-video";
    }
}
