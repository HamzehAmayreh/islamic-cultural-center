package com.ju.islamicculturalcenter.entity;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @ManyToOne
    @JoinColumn(name = "position_ID")
    private Position position;

    public Person(String creation_Date, String createdById, String updateDate, String updatedById, boolean active, Long id, String firstName, String lastName, String userName, String email, String password, int phoneNumber, String facebookUrl, Position position) {
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
