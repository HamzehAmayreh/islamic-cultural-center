package com.ju.islamicculturalcenter.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Instructor extends Person {

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_volunteer", nullable = false)
    private String isVolunteer;

    @Builder
    public Instructor(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String firstName, String lastName, String userName, String email, String password, int phoneNumber, String facebookUrl, Position position, String imageUrl, String isVolunteer) {
        super(creation_Date, createdById, updateDate, updatedById, active, id, firstName, lastName, userName, email, password, phoneNumber, facebookUrl, position);
        this.imageUrl = imageUrl;
        this.isVolunteer = isVolunteer;
    }
}
