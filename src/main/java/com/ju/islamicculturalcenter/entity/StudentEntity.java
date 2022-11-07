package com.ju.islamicculturalcenter.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "student")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity extends PersonEntity {

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "course_count")
    private Integer courseCount;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Builder
    public StudentEntity(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String firstName, String lastName, String userName, String email, String password, int phoneNumber, String facebookUrl, PositionEntity position, Date dateOfBirth, Integer courseCount, Boolean isVerified) {
        super(creation_Date, createdById, updateDate, updatedById, active, id, firstName, lastName, userName, email, password, phoneNumber, facebookUrl, position);
        this.dateOfBirth = dateOfBirth;
        this.courseCount = courseCount;
        this.isVerified = isVerified;
    }
}
