package com.functionwall.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yu
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerLoginController {

    @GetMapping(value = "/login")
    public String managerLogin() {

        return "manager/manager-login";
    }
}
