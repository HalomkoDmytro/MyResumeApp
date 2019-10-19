package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myresume.utils.LanguageLevelConverter;
import com.myresume.utils.LanguageTypeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Language implements Serializable, Comparable<Language>, ProfileCollectionField {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column
    @NotNull
    @Size(max = 30)
    private String name;

    @Column(name = "level")
    @Convert(converter = LanguageLevelConverter.class)
    private LanguageLevel languageLevel;

    @Column(name = "type")
    @Convert(converter = LanguageTypeConverter.class)
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
