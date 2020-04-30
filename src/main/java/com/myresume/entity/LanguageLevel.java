package com.myresume.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum LanguageLevel {
    BEGINNER,

    ELEMENTARY,

    PRE_INTERMEDIATE,

    INTERMEDIATE,

    UPPER_INTERMEDIATE,

    ADVANCED,

    PROFICIENCY;

    public static LanguageLevel getDbValue(String languageLevel) {
        return LanguageLevel.valueOf(languageLevel.toUpperCase());
    }

    public  String toDbValue() {
        return name().toLowerCase();
    }

    @Converter
    public static class LanguageLevelConverter implements AttributeConverter<LanguageLevel, String> {

        @Override
        public String convertToDatabaseColumn(LanguageLevel language) {
            return language.toDbValue();
        }

        @Override
        public LanguageLevel convertToEntityAttribute(String dbData) {
            return LanguageLevel.getDbValue(dbData);
        }
    }
}
