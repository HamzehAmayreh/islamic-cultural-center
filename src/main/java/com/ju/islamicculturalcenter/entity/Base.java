package com.ju.islamicculturalcenter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Base {

    @Column(name = "creation_date", nullable = false)
    protected Timestamp creation_Date;

    @Column(name = "created_by_id", nullable = false)
    protected Long createdById;

    @Column(name = "update_date", nullable = false)
    protected Timestamp updateDate;

    @Column(name = "updated_by_id", nullable = false)
    protected Long updatedById;

    @Column(name = "active", nullable = false)
    protected Boolean active;

    public Base(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active) {
        this.creation_Date = creation_Date;
        this.createdById = createdById;
        this.updateDate = updateDate;
        this.updatedById = updatedById;
        this.active = active;
    }
}
