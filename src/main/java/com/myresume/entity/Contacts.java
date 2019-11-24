package com.myresume.entity;

import com.myresume.annotation.constraints.EnglishLanguage;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class Contacts implements Serializable {

    private static final long serialVersionUID = -3685720846934765841L;

    @Column(length = 256)
    @EnglishLanguage
    private String skype;

    @Column(length = 256)
    @URL(host = "facebook.com")
    private String facebook;

    @Column(length = 256)
    @EnglishLanguage
    private String linkedin;

    @Column(length = 256)
    @EnglishLanguage
    @URL(host="github.com")
    private String github;

    @Column(length = 100)
    @EnglishLanguage
    private String telegram;

    @Override
    public String toString() {
        return "Contacts{" +
                "skype='" + skype + '\'' +
                ", facebook='" + facebook + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", github='" + github + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(skype, contacts.skype) &&
                Objects.equals(facebook, contacts.facebook) &&
                Objects.equals(linkedin, contacts.linkedin) &&
                Objects.equals(github, contacts.github) &&
                Objects.equals(telegram, contacts.telegram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skype, facebook, linkedin, github, telegram);
    }
}
