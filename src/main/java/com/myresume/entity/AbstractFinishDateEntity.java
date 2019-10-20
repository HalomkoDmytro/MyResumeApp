package com.myresume.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
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
}
