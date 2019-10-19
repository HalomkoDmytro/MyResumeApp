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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Course implements Serializable, ProfileCollectionField, Comparable<Course> {

    private static final long serialVersionUID = 4206575925684228495L;

    @Id
    private Long id;

    @Column
    @NotNull
    @Size(max = 60)
    private String name;

    @Column
    @NotNull
    @Size(max = 60)
    private String school;

    @Column(name = "finish_date")
    private Date finishDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profile")
    @JsonBackReference
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
                ", finishDate=" + finishDate +
                ", id_profile=" + profile.getId() +
                '}';
    }
}
