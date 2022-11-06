package com.ju.islamicculturalcenter.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegReqStudentDto {

    private String firstName;

    private String lastName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private Date dateOfBirth;

}
