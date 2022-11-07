package com.ju.islamicculturalcenter.dto.request.instructor;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructorRegistrationRequestDto extends BaseRequestDto {
    private String firstName;

    private String lastName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private String password;

    private String imageUrl;

    private String isVolunteer;

    private String cvUrl;

}
