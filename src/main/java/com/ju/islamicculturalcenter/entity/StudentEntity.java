package com.ju.islamicculturalcenter.entity;

import com.ju.islamicculturalcenter.entity.enums.UserRoleEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "student")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity extends PersonEntity {

    @JoinColumn(name = "role_id")
    @OneToOne
    private UserRoleEntity role;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "course_count")
    private Integer courseCount;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Builder
    public StudentEntity(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String firstName, String lastName, String userName, String email, String password, String phoneNumber, String facebookUrl, PositionEntity position,  UserRoleEntity role, Date dateOfBirth, Integer courseCount, Boolean isVerified) {
        super(creation_Date, createdById, updateDate, updatedById, active, id, firstName, lastName, userName, email, password, phoneNumber, facebookUrl, position);
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.courseCount = courseCount;
        this.isVerified = isVerified;
    }
}
