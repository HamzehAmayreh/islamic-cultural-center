package com.ju.islamicculturalcenter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class PersonEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @ManyToOne
    @JoinColumn(name = "position_ID")
    private PositionEntity position;

    public PersonEntity(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String firstName, String lastName, String userName, String email, String password, int phoneNumber, String facebookUrl, PositionEntity position) {
        super(creation_Date, createdById, updateDate, updatedById, active);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.facebookUrl = facebookUrl;
        this.position = position;
    }
}
