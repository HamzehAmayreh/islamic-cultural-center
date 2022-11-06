package com.ju.islamicculturalcenter.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ju.islamicculturalcenter.dto.BaseDto;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminResStudentDto extends BaseDto {

    private Long id;

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
