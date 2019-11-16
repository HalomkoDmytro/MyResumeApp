package com.myresume.controller;

import com.myresume.entity.LanguageLevel;
import com.myresume.entity.LanguageType;
import com.myresume.entity.Profile;
import com.myresume.form.SkillForm;
import com.myresume.service.EditProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class EditProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileController.class);

    private static final String uid = "bernadette-rostenkowski"; // TODO: refactor/delete
//    private static final String uid = "aly-dutta"; // TODO: refactor/delete


    @Autowired
    private EditProfileService editProfileService;

//    @GetMapping("/edit/skills2")
//    public String getEditSkills(Model model) {
//        model.addAttribute("skillCategories", skillCategories.findAllByOrderByIdAsc());
//        return "jsp/edit-skills";
//    }

    @GetMapping(value = "/edit/edit-personal-info")
    public String editProfileGeneral(Model model) throws ParseException {
        final Profile profile = editProfileService.findProfileByUid(uid);
        model.addAttribute("profile", profile);
        model.addAttribute("birthDay", convertDate(profile.getBirthDay()));
        model.addAttribute("tabName", "editGeneral");
        return "jsp/edit/edit-personal-info";
    }

    /**
     * Convert date to String in format YYYY-MM-DD
     * @param
     * @return date in string format
     */
    private String convertDate(Date date) {
        final LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final int day = localDate.getDayOfMonth();
        final int month = localDate.getMonthValue();
        final int year = localDate.getYear();
        StringBuilder sb = new StringBuilder();
        sb.append(year)
                .append("-");
        if (month < 10) {
            sb.append("0");
        }
        sb.append(month)
                .append("-");
        if (day < 10) {
            sb.append("0");
        }
        sb.append(day)
                .toString();
        return sb.toString();
    }

    @GetMapping(value = "/edit/contacts")
    public String editProfileContacts(Model model) {
        model.addAttribute("tabName", "contacts");
        final Profile profile = editProfileService.findProfileByUid(uid);
        model.addAttribute("contacts", profile.getContacts());

        return "jsp/edit/contacts";
    }

    @GetMapping(value = "/edit/skills")
    public String getEditSkill(Model model) {

        model.addAttribute("skillForm", editProfileService.findSkills("aly-dutta")); // TODO: remove hardcode
        model.addAttribute("tabName", "editSkill");
        return gotoSkillsJSP(model);
    }

    @PostMapping(value = "/edit/skills")
    public String saveEditSkill( @ModelAttribute("skillForm") @Valid SkillForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tabName", "editSkill");
            return gotoSkillsJSP(model);
        }

        return "redirect:/profile/mike-ross"; //todo
    }

    @GetMapping(value = "/edit/practical-experience")
    public String editProfilePracticalExperience(Model model) {
        model.addAttribute("tabName", "experience");
        final Profile profile = editProfileService.findProfileByUid(uid);
        model.addAttribute("practics", profile.getPractics());

        return "jsp/edit/experiences";
    }

    @GetMapping(value = "/edit/certificates")
    public String editProfileSertificates(Model model) {
        model.addAttribute("tabName", "certificates");
        final Profile profile = editProfileService.findProfileByUid(uid);
        model.addAttribute("certificates", profile.getCertificates());

        return "jsp/edit/certificate";
    }

    @GetMapping(value = "/edit/courses")
    public String editProfileCourses(Model model) {
        model.addAttribute("tabName", "courses");
        final Profile profile = editProfileService.findProfileByUid(uid);
        model.addAttribute("courses", profile.getCourses());

        return "jsp/edit/courses";
    }

    @GetMapping(value = "/edit/education")
    public String editProfileEducation(Model model) {
        model.addAttribute("tabName", "education");
        final Profile profile = editProfileService.findProfileByUid(uid);
        model.addAttribute("educations", profile.getEducations());

        return "jsp/edit/education";
    }

    @GetMapping(value = "/edit/languages")
    public String editProfileLanguages(Model model) {
        model.addAttribute("tabName", "languages");
        final Profile profile = editProfileService.findProfileByUid(uid);
        model.addAttribute("languages", profile.getLanguages());
        model.addAttribute("languageTypes", LanguageType.values());
        model.addAttribute("languageLevels", LanguageLevel.values());

        return "jsp/edit/languages";
    }

    @GetMapping(value = "/edit/hobbies")
    public String editProfileHobbies(Model model) {
        model.addAttribute("tabName", "hobbies");
        final Profile profile = editProfileService.findProfileByUid(uid);
        model.addAttribute("hobbies", profile.getHobbies());

        return "jsp/edit/hobbies";
    }

    @GetMapping(value = "/edit/additional-info")
    public String editProfileAdditionalInfo(Model model) {
        model.addAttribute("tabName", "additionalInfo");
        final Profile profile = editProfileService.findProfileByUid(uid);
        model.addAttribute("profileInfo", profile.getInfo());

        return "jsp/edit/additional";
    }

    private String gotoSkillsJSP(Model model) {
        model.addAttribute("skillCategories", editProfileService.findSkillCategory());
        return "jsp/edit/skills";
    }
}