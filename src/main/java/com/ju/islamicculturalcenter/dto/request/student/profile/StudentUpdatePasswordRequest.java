package com.ju.islamicculturalcenter.dto.request.student.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentUpdatePasswordRequest {

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;
}
