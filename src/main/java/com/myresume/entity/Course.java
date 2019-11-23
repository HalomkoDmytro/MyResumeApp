package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myresume.annotation.constraints.EnglishLanguage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "profile")
@Entity
public class Course extends AbstractFinishDateEntity<Course> implements Serializable, ProfileCollectionField, Comparable<Course> {

    private static final long serialVersionUID = 4206575925684228495L;

    @Id
    @SequenceGenerator(name = "COURSE_ID_GENERATOR", sequenceName = "COURSE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 60, nullable = false)
    @EnglishLanguage(withSpechSymbols = false)
    private String name;

    @Column(length = 60, nullable = false)
    @EnglishLanguage(withSpechSymbols = false)
    private String school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile")
    @JsonBackReference
//    @JsonIgnore
    private Profile profile;

    @Override
    public int compareTo(Course o) {
        // TODO: implement DataUtils.class
        return 0;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                ", finishDate=" + getFinishDate() +
                ", id_profile=" + profile.getId() +
                '}';
    }
}
