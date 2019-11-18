package com.myresume.entity;


import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.ZoneId;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractFinishDateEntity<T> {

    @Column(name = "finish_date")
    protected Date finishDate;

    @Transient
    private Integer finishDateYear;

    @Transient
    private Integer finishDateMonth;

    @Transient
    public boolean isFinish() {
        return finishDate != null;
    }

    public Integer getFinishDateYear() {
        if (finishDateYear != null) {
            return finishDateYear;
        } else if (isFinish()) {
            return new DateTime(finishDate).getYear();
        }
        return null;
    }

    public Integer getFinishDateMonth() {
        if (finishDateMonth != null) {
            return finishDateMonth;
        } else if (isFinish()) {
            return new DateTime(finishDate).getMonthOfYear();
        }
        return null;
    }

    public void setFinishDateYear(Integer finishDateYear) {
        this.finishDateYear = finishDateYear;
        setupEndDate();
    }

    public void setFinishDateMonth(Integer finishDateMonth) {
        this.finishDateMonth = finishDateMonth;
        setupEndDate();
    }

    private void setupEndDate() {
        if (finishDateYear != null && finishDateMonth != null) {
            setFinishDate(new Date(new DateTime(finishDateYear, finishDateMonth, 1, 0, 0).getMillis()));
        } else {
            setFinishDate(null);
        }
    }
}
