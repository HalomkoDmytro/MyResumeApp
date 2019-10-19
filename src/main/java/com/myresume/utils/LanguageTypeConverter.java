package com.myresume.utils;

import com.myresume.entity.LanguageType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LanguageTypeConverter implements AttributeConverter<LanguageType, String> {

    @Override
    public String convertToDatabaseColumn(LanguageType languageType) {
        return languageType.toDbValue();
    }

    @Override
    public LanguageType convertToEntityAttribute(String s) {
        return LanguageType.getDbValue(s);
    }
}
