package com.myresume.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInSignUpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignInSignUpController.class);

    @GetMapping("/sign-in")
    public String singIn() {
        return "jsp/login";
    }

    @GetMapping("/sign-up")
    public String singUp() {
        return "jsp/signup";
    }

    @GetMapping("/sign-up-success")
    public String singUpSuccess() {
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

}
