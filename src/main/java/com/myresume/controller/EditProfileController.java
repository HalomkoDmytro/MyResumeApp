package com.myresume.controller;

import com.myresume.dao.SkillCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileController.class);

    @Autowired
    private SkillCategoryRepository skillCategories;

    @GetMapping("/edit/personal-info")
    public String editProfilePersonalInfo() {
        return "jsp/edit-personal-info";
    }

    @GetMapping("/edit/skills")
    public String getEditSkills(Model model) {
        model.addAttribute("skillCategories", skillCategories.findAllByOrderByIdAsc());
        return "jsp/edit-skills";
    }

}
