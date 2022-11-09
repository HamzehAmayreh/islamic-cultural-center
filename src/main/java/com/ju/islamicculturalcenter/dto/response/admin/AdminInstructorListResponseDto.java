package com.ju.islamicculturalcenter.dto.response.admin;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AdminInstructorListResponseDto implements BaseResponseDto {

    private Long id;

    private Long firstName;

    private Long lastName;

    private String email;

    private Integer phoneNumber;

    private String imageUrl;

    @Builder
    public AdminInstructorListResponseDto(Long id, Long firstName, Long lastName, String email, Integer phoneNumber, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
    }
}
