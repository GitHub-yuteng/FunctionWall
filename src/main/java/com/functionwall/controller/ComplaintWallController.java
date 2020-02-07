package com.functionwall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Controller
public class ComplaintWallController {

    @GetMapping(value = "complaint-wall")
    public String complaintWall() {
        return "complaint-wall";
    }
}
