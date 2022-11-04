package com.ju.islamicculturalcenter.entity;

import com.ju.islamicculturalcenter.entity.enums.AdminRoles;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "admin")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends Person {

    @Column(name = "role")
    private AdminRoles role;

    @Column(name = "address")
    private String address;

    @Column(name = "iban")
    private String iban;

    @Builder
    public Admin(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String firstName, String lastName, String userName, String email, String password, int phoneNumber, String facebookUrl, Position position, AdminRoles role, String address, String iban) {
        super(creation_Date, createdById, updateDate, updatedById, active, id, firstName, lastName, userName, email, password, phoneNumber, facebookUrl, position);
        this.role = role;
        this.address = address;
        this.iban = iban;
    }
}
