package com.functionwall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Controller
public class LostFoundController {

    @GetMapping(value = "/lost-found")
    public String lostFound() {
        return "lost-found";
    }

    @GetMapping(value = "/lost-found-fullwidth")
    public String lostFoundFullwidth() {
        return "lost-found-fullwidth";
    }

    @GetMapping(value = "/single-portfolio-video")
    public String singlePortfolioVideo() {
        return "single-portfolio-video";
    }
}
