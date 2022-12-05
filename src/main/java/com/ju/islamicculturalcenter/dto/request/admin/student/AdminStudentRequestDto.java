package com.ju.islamicculturalcenter.dto.request.admin.student;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminStudentRequestDto implements BaseRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String facebookUrl;

    private Date dateOfBirth;
}
