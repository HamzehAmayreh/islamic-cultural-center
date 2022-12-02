package com.ju.islamicculturalcenter.dto.request.student;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRegistrationRequestDto implements BaseRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String facebookUrl;

    private Date dateOfBirth;

}
