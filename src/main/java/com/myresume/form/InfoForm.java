package com.myresume.form;

import com.myresume.annotation.constraints.EnglishLanguage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InfoForm {

    @EnglishLanguage
    String info;
}
