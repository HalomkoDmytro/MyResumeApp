package com.myresume.controller;

import com.myresume.service.NameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private NameService nameService;

    @GetMapping({"/", "/hello"})
    public String hello() {
        LOGGER.info("GET MAPPING: hello");
        return "index";
    }

    @GetMapping({"/profile"})
    public String profile() {
//        throw new RuntimeException(); // test purpose
        return "jsp/profile";
    }

    @GetMapping({"/profile/{uid}"})
    public String profileWithName(@PathVariable("uid") String uis, Model model) {
        final String s = nameService.convertName(uis);
        model.addAttribute("fullName", s);
        return "jsp/profile";
    }

    @GetMapping("/error")
    public String errorHandler2() {
        return "jsp/error";
    }
}
