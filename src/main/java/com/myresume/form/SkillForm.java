package com.myresume.form;

import com.myresume.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SkillForm implements Serializable {

    @Valid
    private List<Skill> items = new ArrayList<>();
}
