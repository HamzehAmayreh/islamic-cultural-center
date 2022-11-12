package com.ju.islamicculturalcenter.dto.request.instructor;

import com.ju.islamicculturalcenter.entity.PositionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructorUpdateDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private PositionEntity position;

    private String imageUrl;

    private String isVolunteer;

    private Double salary;

    private String cvUrl;

    private String subNumber;
}
