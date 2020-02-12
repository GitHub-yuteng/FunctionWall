package com.functionwall.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Api(tags = "校园活动")
@Controller
public class ActivitiesController {

    @GetMapping(value = "/campus-activities")
    public String campusActivities() {
        return "campus-activities";
    }
}
