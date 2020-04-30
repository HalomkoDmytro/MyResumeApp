package com.myresume.form;

import com.myresume.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CourseFrom {

    @Valid
    private List<Course> items;
}
