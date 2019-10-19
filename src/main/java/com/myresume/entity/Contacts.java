package com.myresume.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class Contacts implements Serializable {

    private static final long serialVersionUID = -3685720846934765841L;

    @Column
    @Size(max = 256)
    private String skype;

    @Column
    @Size(max = 256)
    private String facebook;

    @Column
    @Size(max = 256)
    private String linkedin;

    @Column
    @Size(max = 256)
    private String github;

    @Column
    @Size(max = 100)
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
}
