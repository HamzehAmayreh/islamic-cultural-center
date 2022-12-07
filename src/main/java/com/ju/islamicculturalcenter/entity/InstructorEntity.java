package com.ju.islamicculturalcenter.entity;

import com.ju.islamicculturalcenter.entity.enums.UserRoleEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "instructor")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class InstructorEntity extends PersonEntity {

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_volunteer", nullable = false)
    private Boolean isVolunteer;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "cv_url")
    private String cvUrl;

    @Column(name = "sub_number", nullable = false)
    private String subNumber;

    @JoinColumn(name = "role_id")
    @OneToOne
    private UserRoleEntity role;

    @Builder
    public InstructorEntity(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String firstName, String lastName, String userName, String email, String password, String phoneNumber, String facebookUrl, PositionEntity position, UserRoleEntity role, String imageUrl, Boolean isVolunteer, Double salary, String cvUrl, String subNumber) {
        super(creation_Date, createdById, updateDate, updatedById, active, id, firstName, lastName, userName, email, password, phoneNumber, facebookUrl, position);
        this.imageUrl = imageUrl;
        this.role = role;
        this.isVolunteer = isVolunteer;
        this.salary = salary;
        this.cvUrl = cvUrl;
        this.subNumber = subNumber;
    }
}
