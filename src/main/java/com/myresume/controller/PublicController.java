package com.myresume.controller;

import com.myresume.entity.Profile;
import com.myresume.service.NameService;
import com.myresume.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    private NameService nameService;

    @Autowired
    private TestService service;

    @GetMapping({"/"})
    public String index() {
        return "index";
    }

    @GetMapping({"/profile"})
    public String profile() {
        return "jsp/profile";
    }

    @GetMapping({"/profile/{uid}"})
    public String profileWithName(@PathVariable("uid") String uis, Model model) {
        final String s = nameService.convertName(uis);
        model.addAttribute("fullName", s);
        return "jsp/profile";
    }

    @GetMapping(value = "/testProfile")
    public String testProfile(Model model) {
        final Profile profile = service.profile();

        model.addAttribute("profile", profile);
        model.addAttribute("contacts", profile.getContacts());

        return "jsp/profile";
    }

}
