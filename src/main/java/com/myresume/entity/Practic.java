package com.myresume.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myresume.annotation.constraints.EnglishLanguage;
import com.myresume.annotation.constraints.FirstFieldLessThanSecond;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.joda.time.DateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "practic")
@FirstFieldLessThanSecond(first = "beginDate", second = "finishDate", message = "End date must be after start date.")
// TODO: add EnableFormErrorConversion
public class Practic extends AbstractFinishDateEntity<Practic> implements Serializable, ProfileCollectionField, Comparable<Practic> {

    @Id
    @SequenceGenerator(name = "PRACTIC_ID_GENERATOR", sequenceName = "PRACTIC_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRACTIC_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    @Size(max = 100)
    @EnglishLanguage(withSpechSymbols = false)
    private String position;

    @Column(length = 100, nullable = false)
    @EnglishLanguage(withSpechSymbols = false)
    private String company;

    @Column(name = "begin_date", nullable = false)
    private Date beginDate;

    @Transient
    private Integer beginYear;

    @Transient
    private Integer beginMonth;

    @Column(nullable = false)
    @EnglishLanguage(withSpechSymbols = false)
    private String responsibilities;

    @Column
    @EnglishLanguage
    @URL
    private String demo;

    @Column
    @EnglishLanguage
    @URL
    private String src;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_profile")
    @JsonBackReference
    private Profile profile;

    public Integer getBeginYear() {
        if (beginYear != null) {
            return beginYear;
        } else if (beginDate != null) {
            return new DateTime(beginDate).getYear();
        }
        return null;
    }

    public Integer getBeginMonth() {
        if (beginMonth != null) {
            return beginMonth;
        } else if (beginDate != null) {
            return new DateTime(beginDate).getMonthOfYear();
        }
        return null;
    }

    public void setBeginYear(Integer beginYear) {
        this.beginYear = beginYear;
        setupBeginDate();
    }

    public void setBeginMonth(Integer beginMonth) {
        this.beginMonth = beginMonth;
        setupBeginDate();
    }

    private void setupBeginDate() {
        if (beginYear != null && beginMonth != null) {
            setBeginDate(new Date(new DateTime(beginYear, beginMonth, 1, 0, 0).getMillis()));
        } else {
            setBeginDate(null);
        }
    }

    @Override
    public int compareTo(Practic o) {
        // TODO: implement
        return 0;
    }

    @Override
    public String toString() {
        return "Practic{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                ", beginDate=" + beginDate +
                ", finishDate=" + getFinishDate() +
                ", responsibilities='" + responsibilities + '\'' +
                ", demo='" + demo + '\'' +
                ", src='" + src + '\'' +
//                ", id_profile " + profile.getId() +
                '}';
    }
}
