package com.functionwall.controller;

import com.functionwall.exception.FunctionWallErrorCode;
import com.functionwall.pojo.model.User;
import com.functionwall.pojo.vo.APIResponse;
import com.functionwall.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author Yu
 */
@Api(tags = "注册相关")
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;


    protected UserService getUserService() {
        return userService;
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @ResponseBody
    @PostMapping(value = "/register")
    public APIResponse<User> registerUser(@RequestParam("realname") String realname,
                                          @RequestParam("account") String account,
                                          @RequestParam("password") String password) {

        if (StringUtils.isBlank(realname)
                || StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || null != getUserService().getUserByAccount(account)) {
            return new APIResponse("50001", "注册失败，帐号已存在!");
        }

        getUserService().save(realname, account, password);
        return new APIResponse<>("200");
    }
}
