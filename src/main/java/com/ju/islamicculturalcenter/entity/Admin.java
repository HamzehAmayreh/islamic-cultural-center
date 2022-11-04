package com.ju.islamicculturalcenter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends Person{

    @Column(name = "role")
    private String role;

    @Column(name = "address")
    private String address;

    @Column(name = "iban")
    private String iban;

    public Admin(String creation_Date, String createdById, String updateDate, String updatedById, boolean active, Long id, String firstName, String lastName, String userName, String email, String password, int phoneNumber, String facebookUrl, Position position, String role, String address, String iban) {
        super(creation_Date, createdById, updateDate, updatedById, active, id, firstName, lastName, userName, email, password, phoneNumber, facebookUrl, position);
        this.role = role;
        this.address = address;
        this.iban = iban;
    }
}
