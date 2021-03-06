package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myresume.annotation.constraints.EnglishLanguage;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Skill implements Serializable, ProfileCollectionField, ProfileEntity, Comparable<Skill> {

    @Id
    @SequenceGenerator(name = "SKILL_ID_GENERATOR", sequenceName = "SKILL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SKILL_ID_GENERATOR")
    @Column(unique = true)
    private Long id;

    @Column(length = 50)
    @EnglishLanguage
    @Size(min = 1)
    private String category;

    @Column
    @EnglishLanguage(message = "Use English Language")
    @Size(min = 1)
    private String value;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profile")
    @JsonBackReference
    private Profile profile;

    @Override
    public int compareTo(Skill o) {
        return getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) &&
                Objects.equals(category, skill.category) &&
                Objects.equals(value, skill.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, value);
    }
}
