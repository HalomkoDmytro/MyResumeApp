package com.myresume.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonalInfo.class);

    @GetMapping("/edit-personal-info")
    public String editProfilePersonalInfo() {
        return "jsp/edit-personal-info";
    }

}
