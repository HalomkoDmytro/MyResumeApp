package com.myresume.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("/resumeError")
    public String resumeError() {
        return "jsp/error";
    }
}
