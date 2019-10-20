package com.myresume.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum LanguageType {

    ALL,

    SPOKEN,

    WRITING;

    public static LanguageType getDbValue(String languageType) {
        return LanguageType.valueOf(languageType.toUpperCase());
    }

    public String toDbValue() {
        return this.name().toLowerCase();
    }

    public LanguageType getReverseType() {
        if (this == SPOKEN) {
            return WRITING;
        } else if (this == WRITING) {
            return SPOKEN;
        } else {
            throw new IllegalArgumentException(this + "does not have reverse type.");
        }
    }

    @Converter
    public static class LanguageTypeConverter implements AttributeConverter<LanguageType, String> {

        @Override
        public String convertToDatabaseColumn(LanguageType languageType) {
            return languageType.toDbValue();
        }

        @Override
        public LanguageType convertToEntityAttribute(String s) {
            return getDbValue(s);
        }
    }

}
