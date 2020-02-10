package com.functionwall.controller;

import com.functionwall.pojo.mould.User;
import com.functionwall.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yu
 */
@Controller
public class LoginController {

    private static final transient Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        Model model) {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            model.addAttribute("msg", "用户名或密码为空");
            return "login";
        }

        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);

        try {
            currentUser.login(token);
            User user = getUserService().getUserByAccount(account);
            Session session = currentUser.getSession();
            session.setAttribute("user", user);
            return "index";
        } catch (UnknownAccountException uae) {
            log.info("There is no user with username of " + token.getPrincipal());
            model.addAttribute("msg", "用户不存在，请注册！");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            model.addAttribute("msg", "密码错误！");
            return "login";
        }
    }

    @GetMapping(value = "/logout")
    public View logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/index");
    }
}