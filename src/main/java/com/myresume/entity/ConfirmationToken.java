package com.myresume.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "confirmation_token")
public class ConfirmationToken {

    @Id
    @SequenceGenerator(name = "CERTIFICATE_ID_GENERATOR", sequenceName = "CONFIRMATION_TOKEN_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CERTIFICATE_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Profile.class)
    @JoinColumn(name = "id_profile")
    private Profile profile;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date createdDate;

    public ConfirmationToken(Profile profile) {
        this.profile = profile;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
