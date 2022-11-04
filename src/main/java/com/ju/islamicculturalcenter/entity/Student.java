package com.ju.islamicculturalcenter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person{

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "course_count")
    private Integer courseCount;

    @Column(name = "is_verified")
    private Boolean isVerified;



}
