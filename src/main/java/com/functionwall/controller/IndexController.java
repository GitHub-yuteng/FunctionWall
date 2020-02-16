package com.functionwall.controller;

import io.swagger.annotations.Api;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Api(tags = "页面跳转")
@Controller
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }


    @GetMapping(value = "/my-info")
    public String aboutUs() {
        return "my-info";
    }

    @GetMapping(value = "/special-price")
    public String specialPrice() {
        return "special-price";
    }

    @GetMapping(value = "/404")
    public String notFound() {
        return "404";
    }

}