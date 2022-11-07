package com.ju.islamicculturalcenter.dto.response;

import com.ju.islamicculturalcenter.entity.Position;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegResInstructorDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private Position position;

    private String imageUrl;

    private String isVolunteer;

    private Double salary;

    private String cvUrl;

    private String subNumber;



}
