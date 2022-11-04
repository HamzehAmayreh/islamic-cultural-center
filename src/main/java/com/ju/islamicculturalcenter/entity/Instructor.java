package com.ju.islamicculturalcenter.entity;

import lombok.*;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends Person{



    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_volunteer",nullable = false)
    private String isVolunteer;


    public Instructor(String creation_Date, String createdById, String updateDate, String updatedById, boolean active, Long id, String firstName, String lastName, String userName, String email, String password, int phoneNumber, String facebookUrl, Position position, String imageUrl, String isVolunteer) {
        super(creation_Date, createdById, updateDate, updatedById, active, id, firstName, lastName, userName, email, password, phoneNumber, facebookUrl, position);
        this.imageUrl = imageUrl;
        this.isVolunteer = isVolunteer;
    }
}
