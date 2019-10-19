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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "education")
public class Education implements Serializable, ProfileCollectionField, Comparable<Education> {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column
    @NotNull
    @Size(max = 255)
    private String faculty;

    @Column
    @NotNull
    @Size(max = 100)
    private String summary;

    @Column
    @NotNull
    private String university;

    @Column(name = "begin_year")
    @NotNull
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
                ", id_profile=" + profile.getId() +
                '}';
    }
}
