package com.myresume.controller;

import com.myresume.entity.Contacts;
import com.myresume.entity.LanguageLevel;
import com.myresume.entity.LanguageType;
import com.myresume.entity.Profile;
import com.myresume.form.CourseFrom;
import com.myresume.form.EducationForm;
import com.myresume.form.GeneralInfoForm;
import com.myresume.form.InfoForm;
import com.myresume.form.LanguageForm;
import com.myresume.form.PracticForm;
import com.myresume.form.SkillForm;
import com.myresume.service.EditProfileService;
import com.myresume.service.FindProfileService;
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

@Controller
public class EditProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileController.class);

    private static final String uid = "bernadette-rostenkowski"; // TODO: refactor/delete
//    private static final String uid = "aly-dutta"; // TODO: refactor/delete

    @Autowired
    private FindProfileService findProfileService;

    @Autowired
    private EditProfileService editProfileService;

    @GetMapping(value = "/edit/edit-personal-info")
    public String editProfileGeneral(Model model) throws ParseException {
        final Profile profile = findProfileService.findProfileByUid(uid);
        model.addAttribute("profile", profile);
        model.addAttribute("birthDay", profile.getBirthDay());
        model.addAttribute("tabName", "editGeneral");
        return "jsp/edit/edit-personal-info";
    }

    @PostMapping(value = "/edit/edit-personal-info")
    public String saveProfileGeneralInfo(@ModelAttribute("profile") @Valid GeneralInfoForm profileForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            final GeneralInfoForm generalInfo = profileForm;
            model.addAttribute("profile", generalInfo);
            model.addAttribute("birthDay", generalInfo.getBirthDay());
            model.addAttribute("tabName", "editGeneral");
            return "jsp/edit/edit-personal-info";
        }

        return "redirect:/edit/contacts";
    }

    @GetMapping(value = "/edit/contacts")
    public String editProfileContacts(Model model) {
        model.addAttribute("tabName", "contacts");
        final Profile profile = findProfileService.findProfileByUid(uid);
        model.addAttribute("contacts", profile.getContacts());

        return "jsp/edit/contacts";
    }

    @PostMapping(value = "/edit/contacts")
    public String saveContact(@ModelAttribute("contacts") @Valid Contacts contacts, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tabName", "contacts");
            model.addAttribute("contacts", contacts);

            return "jsp/edit/contacts";
        }

        return "redirect:/edit/skills";
    }

    @GetMapping(value = "/edit/skills")
    public String getEditSkill(Model model) {

        model.addAttribute("skillForm", editProfileService.findSkillsByUid("aly-dutta")); // TODO: remove hardcode
        return gotoSkillsJSP(model);
    }

    @PostMapping(value = "/edit/skills")
    public String saveEditSkill(@ModelAttribute("skillForm") @Valid SkillForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return gotoSkillsJSP(model);
        }

        return "redirect:/edit/practical-experience"; //todo
    }

    @GetMapping(value = "/edit/practical-experience")
    public String editProfilePracticalExperience(Model model) {
        model.addAttribute("tabName", "experience");
        final Profile profile = findProfileService.findProfileByUid(uid);
        model.addAttribute("practicForm", new PracticForm(profile.getPractices()));

        return "jsp/edit/experiences";
    }

    @PostMapping(value = "/edit/practical-experience")
    public String savePracticalExperience(@ModelAttribute("practicForm") @Valid PracticForm practicForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tabName", "experience");
            model.addAttribute("practicForm", practicForm);
            return "jsp/edit/experiences";
        }

        return "redirect:/edit/certificates"; //todo
    }

    @GetMapping(value = "/edit/certificates")
    public String editProfileSertificates(Model model) {
        model.addAttribute("tabName", "certificates");
        final Profile profile = findProfileService.findProfileByUid(uid);
        model.addAttribute("certificates", profile.getCertificates());

        return "jsp/edit/certificate";
    }

    @GetMapping(value = "/edit/courses")
    public String editProfileCourses(Model model) {
        model.addAttribute("tabName", "courses");
        final Profile profile = findProfileService.findProfileByUid(uid);
        model.addAttribute("courseFrom", new CourseFrom(profile.getCourses()));

        return "jsp/edit/courses";
    }

    @PostMapping(value = "/edit/courses")
    public String saveProfileCourses(@ModelAttribute("courseFrom") @Valid CourseFrom courseFrom, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tabName", "courses");
            model.addAttribute("courseFrom", courseFrom);
            return "jsp/edit/courses";
        }

        return "redirect:/edit/education"; //todo
    }

    @GetMapping(value = "/edit/education")
    public String editProfileEducation(Model model) {
        model.addAttribute("tabName", "education");
        final Profile profile = findProfileService.findProfileByUid(uid);
        model.addAttribute("educationForm", new EducationForm(profile.getEducations()));

        return "jsp/edit/education";
    }

    @PostMapping(value = "/edit/education")
    public String saveProfileEducation(@ModelAttribute("educationForm") @Valid EducationForm educationForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tabName", "education");
            model.addAttribute("educationForm", educationForm);
            return "jsp/edit/education";
        }

        return "redirect:/edit/languages";
    }

    @GetMapping(value = "/edit/languages")
    public String editProfileLanguages(Model model) {
        model.addAttribute("tabName", "languages");
        final Profile profile = findProfileService.findProfileByUid(uid);
        model.addAttribute("languageForm", new LanguageForm(profile.getLanguages()));
        model.addAttribute("languageTypes", LanguageType.values());
        model.addAttribute("languageLevels", LanguageLevel.values());

        return "jsp/edit/languages";
    }

    @PostMapping(value = "/edit/languages")
    public String saveProfileLanguages(@ModelAttribute("languageForm") @Valid LanguageForm languageForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("languageTypes", LanguageType.values());
            model.addAttribute("languageLevels", LanguageLevel.values());
            model.addAttribute("tabName", "languages");
            model.addAttribute("educationForm", languageForm);
            return "jsp/edit/languages";
        }

        return "redirect:/edit/hobbies";
    }

    @GetMapping(value = "/edit/hobbies")
    public String editProfileHobbies(Model model) {
        model.addAttribute("tabName", "hobbies");
        final Profile profile = findProfileService.findProfileByUid(uid);
        model.addAttribute("hobbies", profile.getHobbies());
        // TODO replace with findHobbiesWithProfileSelected

        return "jsp/edit/hobbies";
    }

    @GetMapping(value = "/edit/info")
    public String editProfileAdditionalInfo(Model model) {
        model.addAttribute("tabName", "additionalInfo");
        final Profile profile = findProfileService.findProfileByUid(uid);
        model.addAttribute("infoForm", new InfoForm(profile.getInfo()));

        return "jsp/edit/info";
    }

    @PostMapping(value = "/edit/info")
    public String saveProfileInfo(@ModelAttribute("infoForm") @Valid InfoForm infoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tabName", "additionalInfo");
            model.addAttribute("infoForm", infoForm);
            return "jsp/edit/info";
        }

        return "redirect:/edit/info";
    }

    private String gotoSkillsJSP(Model model) {
        model.addAttribute("skillCategories", editProfileService.findSkillCategory());
        model.addAttribute("tabName", "editSkill");
        return "jsp/edit/skills";
    }
}