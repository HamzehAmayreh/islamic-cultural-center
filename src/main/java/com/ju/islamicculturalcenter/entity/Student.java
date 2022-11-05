package com.ju.islamicculturalcenter.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "student")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends Person {

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "course_count")
    private Integer courseCount;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Builder
    public Student(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String firstName, String lastName, String userName, String email, String password, int phoneNumber, String facebookUrl, Position position, String dateOfBirth, Integer courseCount, Boolean isVerified) {
        super(creation_Date, createdById, updateDate, updatedById, active, id, firstName, lastName, userName, email, password, phoneNumber, facebookUrl, position);
        this.dateOfBirth = dateOfBirth;
        this.courseCount = courseCount;
        this.isVerified = isVerified;
    }
}
