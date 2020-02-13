package com.functionwall.controller;

import com.functionwall.pojo.model.User;
import com.functionwall.pojo.vo.APIResponse;
import com.functionwall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public View updateUsernameById(@RequestParam String username,
                                   @RequestParam String userId,
                                   HttpServletRequest request) {
        String contextPath = request.getContextPath();
        getUserService().updateUsernameById(userId, username);
        User user = getUserService().getUserById(userId);
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("user", user);
        return new RedirectView(contextPath + "/my-info");
    }

    /**
     * 修改密码
     */
    @ApiOperation("修改密码")
    @PutMapping(value = "/password")
    public String updatePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String userId,
                                 Model model) {
        Boolean flag = getUserService().updatePasswordById(userId, oldPassword, newPassword);

        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();

        if (flag) {
            currentUser.logout();
            return "/login";
        } else {
            model.addAttribute("msg", "修改密码失败！");
            return "my-info";
        }
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
