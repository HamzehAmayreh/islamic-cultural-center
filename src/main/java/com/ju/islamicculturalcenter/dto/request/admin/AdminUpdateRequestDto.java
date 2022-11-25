package com.ju.islamicculturalcenter.dto.request.admin;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminUpdateRequestDto implements BaseRequestDto {

    private String firstName;

    private String lastName;

    private Integer phoneNumber;

    private String facebookUrl;

    private String address;

    private String iban;
}
