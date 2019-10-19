package com.myresume.entity;

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

    public String toDbValue() {
        return this.name().toLowerCase();
    }
}
