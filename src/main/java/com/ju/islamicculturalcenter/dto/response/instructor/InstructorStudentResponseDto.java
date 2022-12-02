package com.ju.islamicculturalcenter.dto.response.instructor;

import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorStudentResponseDto implements BaseResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String phoneNumber;

    private String facebookUrl;

    private Date dateOfBirth;

    private Integer courseCount;

    private Boolean isVerified;

    private List<InstructorCourseListResponseDto> courses;
}
