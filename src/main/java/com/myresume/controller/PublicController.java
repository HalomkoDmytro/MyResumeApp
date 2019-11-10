package com.myresume.controller;

import com.myresume.dao.ProfileRepository;
import com.myresume.dao.SkillCategoryRepository;
import com.myresume.entity.Profile;
import com.myresume.service.NameService;
import com.myresume.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    private NameService nameService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @GetMapping({"/"})
    public String index() {
        return "index";
    }

    @GetMapping({"/profile"})
    public String profile() {
        return "jsp/profile";
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam(required = false) String page, Model model) {
        final Page<Profile> chunkCompletedProfiles = profileService.getChunkCompletedProfiles(page);
        model.addAttribute("profiles", chunkCompletedProfiles.getContent());
        return "jsp/welcome";
    }

    @GetMapping({"/profile/{uid}"})
    public String profileWithName(@PathVariable("uid") String uis, Model model) {
        final Profile profile = profileRepository.findByUid(uis);
        if (profile == null) {
            return "jsp/profile-not-found";
        }
        model.addAttribute("profile", profile);
        model.addAttribute("contacts", profile.getContacts());
        return "jsp/profile";
    }

    private String gotoSkillsJSP(Model model) {
        model.addAttribute("skillCategories", skillCategoryRepository.findAll());
        return "jsp/edit/skills";
    }

//    @GetMapping(value = "/test")
//    public String test(Model model) {
//        return "jsp/edit/edit-personal-info";
//    }

}
