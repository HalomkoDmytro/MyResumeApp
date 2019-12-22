package com.myresume.form;

import com.myresume.annotation.constraints.Adulthood;
import com.myresume.annotation.constraints.EnglishLanguage;
import com.myresume.annotation.constraints.Phone;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class GeneralInfoForm {

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @EnglishLanguage(withSpechSymbols = false)
    @Size(max = 60)
    private String country;

    @EnglishLanguage(withSpechSymbols = false)
    @Size(max = 100)
    private String city;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Adulthood
    private Date birthDay;

    @Size(max = 256)
    private String largePhoto;

    @Size(max = 256)
    private String smallPhoto;

    @Size(max = 50)
    @Phone
    private String phone;

    @Size(max = 100)
    @EnglishLanguage
    private String email;


    @EnglishLanguage
    private String objective;

    @EnglishLanguage
    private String summary;

}
