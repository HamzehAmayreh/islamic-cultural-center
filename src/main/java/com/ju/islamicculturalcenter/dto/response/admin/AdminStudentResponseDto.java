package com.ju.islamicculturalcenter.dto.response.admin;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminStudentResponseDto extends BaseResponseDto {

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private Date dateOfBirth;

    private Integer courseCount;

    private Boolean isVerified;

    @Builder
    public AdminStudentResponseDto(Long id, Long createdById, Long editedById, Timestamp creationDate, Timestamp editedDate, Boolean isActive, String firstName, String lastName, String userName, String email, Integer phoneNumber, String facebookUrl, Date dateOfBirth, Integer courseCount, Boolean isVerified) {
        super(id, createdById, editedById, creationDate, editedDate, isActive);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.facebookUrl = facebookUrl;
        this.dateOfBirth = dateOfBirth;
        this.courseCount = courseCount;
        this.isVerified = isVerified;
    }
}
