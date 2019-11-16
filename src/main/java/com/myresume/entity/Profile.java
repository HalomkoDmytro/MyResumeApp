package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.myresume.annotation.constraints.Adulthood;
import com.myresume.annotation.constraints.EnglishLanguage;
import com.myresume.annotation.constraints.Phone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Profile {

    @Id
    @SequenceGenerator(name = "PROFILE_ID_GENERATOR", sequenceName = "PROFILE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    @Adulthood
    private Date birthDay;

    @Column(length = 100)
    @EnglishLanguage(withSpechSymbols = false)
    private String city;

    @Column(length = 60)
    @EnglishLanguage(withSpechSymbols = false, withNumber = false)
    private String country;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column
    @NotNull
    @SafeHtml
    @EnglishLanguage
    private String objective;

    @Column(length = 256)
    private String largePhoto;

    @Column(length = 256)
    private String smallPhoto;

    @Column(length = 50)
    @Phone
    private String phone;

    @Column(length = 100)
    @NotNull
    @EnglishLanguage
    @Email
    private String email;

    @Column
    private String info;

    @Column
    @EnglishLanguage
    @SafeHtml
    private String summary;

    @Column(length = 100, nullable = false)
    private String uid;

    @Column(length = 256, nullable = false)
    private String password;

    @Column
    private boolean completed;

    @Column(nullable = false)
    private Date created;

    @OneToMany(targetEntity = Certificate.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Certificate> certificates;

    @OneToMany(targetEntity = Education.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("finishYear DESC, beginYear DESC, id DESC")
    private List<Education> educations;

    @OneToMany(targetEntity = Hobby.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("name ASC")
    @JsonManagedReference
    private List<Hobby> hobbies;

    @OneToMany(targetEntity = Language.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Language> languages;

    @OneToMany(targetEntity = Practic.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("finishDate DESC")
    @JsonManagedReference
    private List<Practic> practics = new ArrayList<>();

    @OneToMany(targetEntity = Skill.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("category ASC")
    @JsonManagedReference
    private List<Skill> skills;

    @OneToMany(targetEntity = Course.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("finishDate DESC")
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
