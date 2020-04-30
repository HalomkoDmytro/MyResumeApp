package com.myresume.form;

import com.myresume.annotation.constraints.FieldMatch;
import com.myresume.annotation.constraints.PasswordStrength;
import lombok.Getter;
import lombok.Setter;

@FieldMatch(first = "password", second = "passwordConfirm", message = "Password should match!")
@Getter
@Setter
// TODO: add EnableFormErrorConversion
public class PasswordForm {

    @PasswordStrength
    private String password;

    private String passwordConfirm;
}
