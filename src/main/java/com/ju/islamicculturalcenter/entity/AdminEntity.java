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

@Table(name = "admin")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AdminEntity extends PersonEntity {

    @JoinColumn(name = "role_id")
    @OneToOne
    private UserRoleEntity role;

    @Column(name = "address")
    private String address;

    @Column(name = "iban")
    private String iban;

    @Builder
    public AdminEntity(Timestamp creation_Date, Long createdById, Timestamp updateDate, Long updatedById, Boolean active, Long id, String firstName, String lastName, String userName, String email, String password, String phoneNumber, String facebookUrl, PositionEntity position, UserRoleEntity role, String address, String iban) {
        super(creation_Date, createdById, updateDate, updatedById, active, id, firstName, lastName, userName, email, password, phoneNumber, facebookUrl, position);
        this.role = role;
        this.address = address;
        this.iban = iban;
    }
}
