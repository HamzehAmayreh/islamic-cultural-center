package com.ju.islamicculturalcenter.dto.request.admin;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminStudentRequestDto extends BaseRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private Date dateOfBirth;


}
