package com.ju.islamicculturalcenter.dto.request.student.profile;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentSignUpRequestDto implements BaseRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String facebookUrl;

    private LocalDate dateOfBirth;

    private String newPassword;

    private String confirmPassword;
}
