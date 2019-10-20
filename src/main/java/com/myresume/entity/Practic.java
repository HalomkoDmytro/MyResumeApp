package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "practic")
public class Practic extends AbstractFinishDateEntity<Practic> implements Serializable, ProfileCollectionField, Comparable<Practic> {

    @Id
    @SequenceGenerator(name = "PRACTIC_ID_GENERATOR", sequenceName = "PRACTIC_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRACTIC_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    @NotNull
    @Size(max = 100)
    private String position;

    @Column(length = 100, nullable = false)
    private String company;

    @Column(name = "begin_date", nullable = false)
    private Date beginDate;

    @Column(nullable = false)
    private String responsibilities;

    @Column(length = 256)
    private String demo;

    @Column(length = 256)
    private String src;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profile")
    @JsonBackReference
    private Profile profile;

    @Override
    public int compareTo(Practic o) {
        // TODO: implement
        return 0;
    }

    @Override
    public String toString() {
        return "Practic{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                ", beginDate=" + beginDate +
                ", finishDate=" + getFinishDate() +
                ", responsibilities='" + responsibilities + '\'' +
                ", demo='" + demo + '\'' +
                ", src='" + src + '\'' +
                ", id_profile " + profile.getId() +
                '}';
    }
}
