package com.myresume.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class ProfileRestore implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String token;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Profile profile;
}
