package com.myresume.form;

import com.myresume.entity.Practic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PracticForm {

    @Valid
    private List<Practic> items;
}
