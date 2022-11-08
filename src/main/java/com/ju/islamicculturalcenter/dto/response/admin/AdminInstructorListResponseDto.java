package com.ju.islamicculturalcenter.dto.response.admin;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminInstructorListResponseDto implements BaseResponseDto {

    private Long id;

    private Long firstName;

    private Long lastName;

    private String email;

    private Integer phoneNumber;

    private String imageUrl;
}
