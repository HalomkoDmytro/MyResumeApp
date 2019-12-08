package com.myresume.controller;

import com.myresume.entity.CurrentProfile;
import com.myresume.entity.Profile;
import com.myresume.form.SignUpForm;
import com.myresume.service.EditProfileService;
import com.myresume.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class SignInSignUpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignInSignUpController.class);

    @Autowired
    private EditProfileService editProfileService;

    @GetMapping("/sign-in")
    public String singIn() {
        CurrentProfile currentProfile = SecurityUtils.getCurrentProfile();
        if (currentProfile != null) {
            return "redirect:/profile/" + currentProfile.getUsername();
        }
        return "jsp/sign-in";
    }

    @RequestMapping("/sign-in-failed")
    public String singInFailed(HttpSession session) {
        if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null) {
            return "redirect:/sign-in";
        }
        return "jsp/sign-in";
    }

    @GetMapping("/sign-up")
    public String singUp(Model model) {
        CurrentProfile currentProfile = SecurityUtils.getCurrentProfile();
        if (currentProfile != null && currentProfile.getUsername() != null) {
            return "redirect:/profile/" + currentProfile.getUsername();
        }
        model.addAttribute("signUpForm", new SignUpForm());
        return "jsp/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpHandler(@ModelAttribute("signUpForm") @Valid SignUpForm signUpForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("signUpForm", signUpForm);
            return "jsp/sign-up";
        }

        final Profile newProfile = editProfileService.createNewProfile(signUpForm);
        SecurityUtils.authenticate(newProfile);
        model.addAttribute("profile", newProfile);
        LOGGER.debug("CREATE NEW PROFILE AND LOGIN" + newProfile);
        return "jsp/signup-success";
    }

    @GetMapping("/restore-access")
    public String restoreAccess() {
        return "jsp/restore-access";
    }

    @GetMapping("/restore-access-success")
    public String restoreAccessSuccess() {
        return "jsp/restore-access-success";
    }

    @GetMapping("/edit-password")
    public String editPassword() {
        return "jsp/edit-password";
    }

    @GetMapping("/remove-profile")
    public String removeProfile() {
        return "jsp/remove-profile";
    }

    @RequestMapping(value = "/my-profile")
    public String getMyProfile(@AuthenticationPrincipal CurrentProfile currentProfile) {
//        return "redirect:/" + currentProfile.getUsername();
        return "redirect:/profile/" + currentProfile.getUsername();
    }

}
