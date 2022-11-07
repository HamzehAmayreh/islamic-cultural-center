package com.ju.islamicculturalcenter.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RegReqInstructorDto {
    private String firstName;

    private String lastName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private String password;

    private String imageUrl;

    private String isVolunteer;

    private String cvUrl;

}
