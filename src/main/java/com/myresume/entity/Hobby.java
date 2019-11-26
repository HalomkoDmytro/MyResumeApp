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
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Hobby implements Serializable, Comparable<Hobby>, ProfileCollectionField {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "HOBBY_ID_GENERATOR", sequenceName = "HOBBY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOBBY_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 30)
    @EnglishLanguage
    private String name;
//
//    @Transient
//    private boolean selected;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profile")
    @JsonBackReference
//    @JsonIgnore
    private Profile profile;

    @Override
    public int compareTo(Hobby o) {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return String.format("Hobby [name=%s]", name);
    }
}
