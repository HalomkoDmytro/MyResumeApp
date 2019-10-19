package com.myresume.entity;

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


}
