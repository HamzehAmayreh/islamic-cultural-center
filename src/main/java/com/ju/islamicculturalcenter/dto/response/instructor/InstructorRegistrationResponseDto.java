package com.ju.islamicculturalcenter.dto.response.instructor;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.PositionEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorRegistrationResponseDto extends BaseResponseDto {

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private PositionEntity position;

    private String imageUrl;

    private String isVolunteer;

    private Double salary;

    private String cvUrl;

    private String subNumber;

    @Builder
    public InstructorRegistrationResponseDto(Long id, Long createdById, Long editedById, Timestamp creationDate, Timestamp editedDate, Boolean isActive, String firstName, String lastName, String userName, String email, Integer phoneNumber, String facebookUrl, PositionEntity position, String imageUrl, String isVolunteer, Double salary, String cvUrl, String subNumber) {
        super(id, createdById, editedById, creationDate, editedDate, isActive);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.facebookUrl = facebookUrl;
        this.position = position;
        this.imageUrl = imageUrl;
        this.isVolunteer = isVolunteer;
        this.salary = salary;
        this.cvUrl = cvUrl;
        this.subNumber = subNumber;
    }
}
