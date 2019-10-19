package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Profile {

    @Id
    private Long id;

    @Column
    private Date birthDay;

    @Column
    @Size(max = 100)
    private String city;

    @Column
    @Size(max = 60)
    private String country;

    @Column
    @NotNull
    @Size(max = 50)
    private String firstName;

    @Column
    @NotNull
    @Size(max = 50)
    private String lastName;

    @Column
    private String objective;

    @Column
    @Size(max = 256)
    private String largePhoto;

    @Column
    @Size(max = 256)
    private String smallPhoto;

    @Column
    @Size(max = 50)
    private String phone;

    @Column
    @Size(max = 100)
    private String email;

    @Column
    private String info;

    @Column
    private String summary;

    @Column
    @NotNull
    @Size(max = 100)
    private String uid;

    @Column
    @NotNull
    @Size(max = 256)
    private String password;

    @Column
    private boolean completed;

    @Column
    @NotNull
    private Date created;

    @OneToMany(targetEntity = Certificate.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Certificate> certificates;

    @OneToMany(targetEntity = Education.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Education> educations;

    @OneToMany(targetEntity = Hobby.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Hobby> hobbies;

    @OneToMany(targetEntity = Language.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Language> languages;

    @OneToMany(targetEntity = Practic.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Practic> practics = new ArrayList<>();

    @OneToMany(targetEntity = Skill.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Skill> skills;

    @OneToMany(targetEntity = Course.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Course> courses;

    @JsonIgnore
    @Embedded
    private Contacts contacts;


    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", birthDay=" + birthDay +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", objective='" + objective + '\'' +
                ", largePhoto='" + largePhoto + '\'' +
                ", smallPhoto='" + smallPhoto + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", info='" + info + '\'' +
                ", summary='" + summary + '\'' +
                ", uid='" + uid + '\'' +
                ", password='" + password + '\'' +
                ", completed=" + completed +
                ", created=" + created +
                '}';
    }
}
