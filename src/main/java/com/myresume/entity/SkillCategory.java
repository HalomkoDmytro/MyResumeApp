package com.myresume.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "skill_category")
public class SkillCategory {

    private static final long serialVersionUID = -8959739023562086833L;
    public static final String ORDER_FIELD_NAME = "idCategory";

    @Id
    private Short id;

    @Column
    @Size(max = 50)
    private String category;

    public SkillCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s]", getClass().getSimpleName(), getId());
    }
}
