package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Skill implements Serializable, ProfileCollectionField, Comparable<Skill> {

    @Id
    private Long id;

    @Column
    @Size(max = 50)
    private String category;

    @Column
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
                ", profile_id=" + profile.getId() +
                '}';
    }
}
