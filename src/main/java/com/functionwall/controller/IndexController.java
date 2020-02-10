package com.functionwall.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/","/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = "/masonry")
    public String indexMasonry() {
        return "index-masonry";
    }

    @GetMapping(value = "/agency")
    public String indexAgency() {
        return "index-agency";
    }

    @GetMapping(value = "/my-info")
    public String aboutUs() {
        return "my-info";
    }

    @GetMapping(value = "/copy")
    public String indexCopy() {
        return "index-copy";
    }

    @GetMapping(value = "/404")
    public String notFound() {
        return "404";
    }
}