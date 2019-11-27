package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myresume.annotation.constraints.EnglishLanguage;
import com.myresume.annotation.constraints.FirstFieldLessThanSecond;
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
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "education")
@FirstFieldLessThanSecond(first = "beginYear", second = "finishYear", message = "Begin date must be before finish date!")
// TODO: add EnableFormErrorConversion
public class Education implements Serializable, ProfileCollectionField, Comparable<Education>, ProfileEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "EDUCATION_ID_GENERATOR", sequenceName = "EDUCATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EDUCATION_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    @EnglishLanguage(withSpechSymbols = false)
    private String faculty;

    @Column(length = 100)
    @EnglishLanguage(withSpechSymbols = false)
    private String summary = "";

    @Column(nullable = false)
    @EnglishLanguage(withSpechSymbols = false)
    private String university;

    @Column(name = "begin_year")
    private Integer beginYear;

    @Column(name = "end_year")
    private Integer finishYear;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profile")
    @JsonBackReference
    private Profile profile;

    @Override
    public int compareTo(Education o) {
        // TODO: implement DataUtil.class
        return 0;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", faculty='" + faculty + '\'' +
                ", summary='" + summary + '\'' +
                ", university='" + university + '\'' +
                ", beginYear=" + beginYear +
                ", finishYear=" + finishYear +
                '}';
    }
}
