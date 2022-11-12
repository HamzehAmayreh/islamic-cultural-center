package com.ju.islamicculturalcenter.dto.request.instructor;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructorResetPasswordRequestDto implements BaseRequestDto {

    private Long id;

    private String newPassword;

    private String confirmPassword;
}
