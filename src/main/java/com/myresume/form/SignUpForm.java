package com.myresume.form;

import com.myresume.annotation.constraints.EnglishLanguage;
import com.myresume.annotation.constraints.FieldMatch;
import com.myresume.annotation.constraints.PasswordStrength;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@FieldMatch(first = "password", second = "confirmPassword", message = "Password should much.")
public class SignUpForm {

    @NotNull
    @Size(max = 50)
    @EnglishLanguage(withSpechSymbols = false)
    private String firstName;

    @NotNull
    @Size(max = 50)
    @EnglishLanguage(withSpechSymbols = false)
    private String lastName;

//    @PasswordStrength
    private String password;

    private String confirmPassword;
}
