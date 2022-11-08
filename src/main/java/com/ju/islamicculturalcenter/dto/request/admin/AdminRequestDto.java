package com.ju.islamicculturalcenter.dto.request.admin;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import com.ju.islamicculturalcenter.entity.enums.AdminRoles;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDto implements BaseRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private AdminRoles role;

    private String address;

    private String iban;
}
