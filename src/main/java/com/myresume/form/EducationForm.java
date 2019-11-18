package com.myresume.form;

import com.myresume.entity.Education;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EducationForm {

    @Valid
    private List<Education> items;
}
