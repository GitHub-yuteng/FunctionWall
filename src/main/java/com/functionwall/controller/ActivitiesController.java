package com.functionwall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Controller
public class ActivitiesController {

    @GetMapping(value = "/campus-activities")
    public String campusActivities() {
        return "campus-activities";
    }
}
