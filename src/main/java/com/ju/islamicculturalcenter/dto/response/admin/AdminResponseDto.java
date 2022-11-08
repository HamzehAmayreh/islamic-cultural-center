package com.ju.islamicculturalcenter.dto.response.admin;

import com.ju.islamicculturalcenter.dto.BaseAdminResponse;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.enums.AdminRoles;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class AdminResponseDto extends BaseAdminResponse implements BaseResponseDto {

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private AdminRoles role;

    private String address;

    private String iban;

    @Builder
    public AdminResponseDto(Long id, Long createdById, Long editedById, Timestamp creationDate, Timestamp editedDate, Boolean isActive, String firstName, String lastName, String userName, String email, Integer phoneNumber, String facebookUrl, AdminRoles role, String address, String iban) {
        super(id, createdById, editedById, creationDate, editedDate, isActive);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.facebookUrl = facebookUrl;
        this.role = role;
        this.address = address;
        this.iban = iban;
    }
}
