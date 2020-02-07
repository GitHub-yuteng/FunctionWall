package com.functionwall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Controller
public class RegisterController {

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }
}
