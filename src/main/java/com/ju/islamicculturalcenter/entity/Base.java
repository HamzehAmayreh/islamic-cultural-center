package com.ju.islamicculturalcenter.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Base {

    @Column(name = "creation_date")
    protected String creation_Date;

    @Column(name = "created_by_id")
    protected String createdById;

    @Column(name = "update_date")
    protected String updateDate;

    @Column(name = "updated_by_id")
    protected String updatedById;

    @Column(name = "active")
    protected boolean active;


    public Base(String creation_Date, String createdById, String updateDate, String updatedById, boolean active) {
        this.creation_Date = creation_Date;
        this.createdById = createdById;
        this.updateDate = updateDate;
        this.updatedById = updatedById;
        this.active = active;
    }
}
