package com.ju.islamicculturalcenter.dto.response.student;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRegistrationResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private Date dateOfBirth;

    private Integer courseCount;

}
