package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.myresume.annotation.constraints.Adulthood;
import com.myresume.annotation.constraints.EnglishLanguage;
import com.myresume.annotation.constraints.Phone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

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
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.hibernate.validator.constraints.SafeHtml;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Document(indexName = "profilei", type = "profile")
public class Profile {

    @Id
    @SequenceGenerator(name = "PROFILE_ID_GENERATOR", sequenceName = "PROFILE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_ID_GENERATOR")
    private Long id;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column
    @Adulthood
    private Date birthDay;

    @Column(length = 100)
    @EnglishLanguage(withSpechSymbols = false)
    private String city;

    @Column(length = 60)
    @EnglishLanguage(withSpechSymbols = false, withNumber = false)
    private String country;

    @Column(length = 50, name = "first_name")
    private String firstName;

    @Column(length = 50, name = "last_name")
    private String lastName;

    @Column
    @NotNull
    @EnglishLanguage
    private String objective;

    @Column(length = 256)
    @JsonIgnore
    private String largePhoto;

    @Column(length = 256)
    private String smallPhoto;

    @Column(length = 50)
    @Phone
    private String phone;

    @Column(length = 100)
//    @NotEmpty
    @EnglishLanguage
//    @Email
    private String email;

    @Column
    private String info;

    @Column
    @EnglishLanguage
    private String summary;

    @Column(length = 100)
    private String uid;

    @Column(length = 256)
    @JsonIgnore
    private String password;

    @Column
    @JsonIgnore
    private boolean completed;

    @Column
    @JsonIgnore
    private Date created;

    @OneToMany(targetEntity = Certificate.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Certificate> certificates;

    @OneToMany(targetEntity = Education.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("finishYear DESC, beginYear DESC, id DESC")
    @JsonIgnore
    private List<Education> educations;

    @OneToMany(targetEntity = Hobby.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("name ASC")
    @JsonManagedReference
    @JsonIgnoreProperties("profile")
    private List<Hobby> hobbies;

    @OneToMany(targetEntity = Language.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Language> languages;

    @OneToMany(targetEntity = Practic.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("finishDate DESC")
    @JsonManagedReference
    private List<Practic> practices = new ArrayList<>();

    @OneToMany(targetEntity = Skill.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("category ASC")
    @JsonManagedReference
    private List<Skill> skills;

    @OneToMany(targetEntity = Course.class, mappedBy = "profile", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("finishDate DESC")
    @JsonManagedReference
    @JsonIgnoreProperties("profile")
    private List<Course> courses;

    @JsonIgnore
    @Embedded
    private Contacts contacts;

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
        updateListSetProfile(this.skills);
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
        updateListSetProfile(this.skills);
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
        updateListSetProfile(this.educations);
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
        updateListSetProfile(this.hobbies);
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
        updateListSetProfile(this.languages);
    }

    public void setPractices(List<Practic> practices) {
        this.practices = practices;
        updateListSetProfile(this.practices);
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        updateListSetProfile(this.courses);
    }

    private void updateListSetProfile(List<? extends ProfileEntity> list) {
        if (list != null) {
            for (ProfileEntity entity : list) {
                entity.setProfile(this);
            }
        }
    }

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
