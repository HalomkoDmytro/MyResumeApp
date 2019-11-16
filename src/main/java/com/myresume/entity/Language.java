package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myresume.annotation.constraints.EnglishLanguage;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Language implements Serializable, Comparable<Language>, ProfileCollectionField {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "LANGUAGE_ID_GENERATOR", sequenceName = "LANGUAGE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANGUAGE_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 30, nullable = false)
    @EnglishLanguage(withSpechSymbols = false, withNumber = false, withPunctuations = false)
    private String name;

    @Column(name = "level")
    @Convert(converter = LanguageLevel.LanguageLevelConverter.class)
    private LanguageLevel languageLevel;

    @Column(name = "type")
    @Convert(converter = LanguageType.LanguageTypeConverter.class)
    private LanguageType languageType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profile")
    @JsonBackReference
    private Profile profile;

    @Override
    public int compareTo(Language o) {
        return name.compareToIgnoreCase(o.getName());
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", languageLevel=" + languageLevel +
                ", languageType=" + languageType +
                ", profile=" + profile +
                '}';
    }
}
