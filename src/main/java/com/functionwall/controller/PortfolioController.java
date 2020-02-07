package com.functionwall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yu
 */
@Controller
public class PortfolioController {

    @GetMapping(value = "/portfolio-grid")
    public String portfolioGrid() {
        return "portfolio-grid";
    }

    @GetMapping(value = "/portfolio-grid-fullwidth")
    public String portfolioGridFullwidth() {
        return "portfolio-grid-fullwidth";
    }

    @GetMapping(value = "/single-portfolio")
    public String singlePortfolio() {
        return "single-portfolio";
    }

    @GetMapping(value = "/single-portfolio-video")
    public String singlePortfolioVideo() {
        return "single-portfolio-video";
    }
}
