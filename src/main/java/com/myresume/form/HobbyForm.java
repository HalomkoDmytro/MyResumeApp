package com.myresume.form;

import com.myresume.entity.Hobby;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class HobbyForm {

    @Valid
    @Size(max = 5)
    private List<Hobby> items;
}
