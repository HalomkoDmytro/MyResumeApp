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

@Entity
@Data
@NoArgsConstructor
public class Certificate implements Serializable, ProfileCollectionField {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column
    @NotNull
    @Size(max = 50)
    private String name;

    @Column(name = "large_url")
    @NotNull
    @Size(max = 256)
    private String largeUrl;

    @Column(name = "small_url")
    @Size(max = 256)
    private String smallUrl;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profile")
    @JsonBackReference
    private Profile profile;

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", largeUrl='" + largeUrl + '\'' +
                ", smallUrl='" + smallUrl + '\'' +
                ", id_profile=" + profile.getId() +
                '}';
    }
}
