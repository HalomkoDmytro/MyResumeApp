package com.myresume.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailModel {

    private String recipient;
    private String subject;
    private String content;

}
