package com.ju.islamicculturalcenter.dto.response;

import com.ju.islamicculturalcenter.dto.BaseDto;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminResStudentDto extends BaseDto {

    private Long id;

    private Timestamp creationDate;

    private Long createdById;

    private Timestamp updateDate;

    private Long updatedById;

    private Boolean isActive;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private Integer phoneNumber;

    private String facebookUrl;

    private Date dateOfBirth;

    private Integer courseCount;

    private Boolean isVerified;


}
