package com.myresume.utils;

import com.myresume.entity.LanguageLevel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LanguageLevelConverter implements AttributeConverter<LanguageLevel, String> {


    @Override
    public String convertToDatabaseColumn(LanguageLevel language) {
        return language.toDbValue();
    }

    @Override
    public LanguageLevel convertToEntityAttribute(String dbData) {
        return LanguageLevel.getDbValue(dbData);
    }
}
