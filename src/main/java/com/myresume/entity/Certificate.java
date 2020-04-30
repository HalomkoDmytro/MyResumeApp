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
public class Certificate implements Serializable, ProfileCollectionField {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CERTIFICATE_ID_GENERATOR", sequenceName = "CERTIFICATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CERTIFICATE_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    @EnglishLanguage
    private String name;

    @Column(name = "large_url", length = 256, nullable = false)
    private String largeUrl;

    @Column(name = "small_url", length = 256)
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
