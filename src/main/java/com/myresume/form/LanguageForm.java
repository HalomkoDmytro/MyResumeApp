package com.myresume.form;

import com.myresume.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LanguageForm {

    @Valid
    private List<Language> items;
}
