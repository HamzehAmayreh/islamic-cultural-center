package com.ju.islamicculturalcenter.dto.request.admin;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminUpdatePasswordRequestDto implements BaseRequestDto {

    private Long id;

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;
}
