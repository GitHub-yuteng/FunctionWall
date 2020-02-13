package com.functionwall.controller;

import com.functionwall.pojo.model.User;
import com.functionwall.pojo.vo.APIResponse;
import com.functionwall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Yu
 */
@Api(tags = "用户相关")
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    protected UserService getUserService() {
        return userService;
    }

    @ApiOperation("查询该用户是否存在")
    @GetMapping(value = "/presence/{username}")
    public APIResponse getUserByUsername(@PathVariable String username) {
        User user = getUserService().getUserByUsername(username);
        return APIResponse.success(user);
    }

    /**
     * 更改网名
     */
    @ApiOperation("更改网名")
    @PutMapping(value = "/username")
    public String updateUsernameById(@RequestParam String username,
                                     @RequestParam String userId) {
        getUserService().updateUsernameById(userId, username);
        return "my-info";
    }

    /**
     * 修改密码
     */
    @ApiOperation("修改密码")
    @PostMapping(value = "/password")
    @ResponseBody
    public APIResponse updatePassword(@RequestParam String oldPassword, @RequestParam String password,
                                      HttpServletRequest request, HttpSession session) {

        return new APIResponse();
    }

    /**
     * 发送邮件
     *
     * @param email
     * @return
     */
    @PostMapping(value = "/email")
    public String addTopic(@RequestParam String email, @RequestParam String userId,
                           @RequestParam String realname) {
        getUserService().sendMail(email, userId, realname);
        return "index";
    }
}
