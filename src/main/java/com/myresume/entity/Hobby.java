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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Hobby implements Serializable, Comparable<Hobby>, ProfileCollectionField {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column
    @NotNull
    @Size(max = 30)
    private String name;

    @Transient
    private boolean selected;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profile")
    @JsonBackReference
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
