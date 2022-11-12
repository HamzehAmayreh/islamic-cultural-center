package com.ju.islamicculturalcenter.dto.request.instructor;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorRequestDto implements BaseRequestDto {
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
