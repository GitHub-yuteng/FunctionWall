package com.functionwall.controller.manager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class AuthController {

    @GetMapping(value = "/login")
    public String login(){
        return "manage/login";
    }
}
