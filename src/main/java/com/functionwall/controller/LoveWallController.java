package com.functionwall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Controller
public class LoveWallController {

    @GetMapping(value = "love-wall")
    public String loveWall() {
        return "love-wall";
    }
}
