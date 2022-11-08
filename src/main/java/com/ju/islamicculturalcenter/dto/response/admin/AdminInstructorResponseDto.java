package com.ju.islamicculturalcenter.dto.response.admin;

import com.ju.islamicculturalcenter.dto.BaseAdminResponse;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.PositionEntity;

import java.util.List;

public class AdminInstructorResponseDto extends BaseAdminResponse implements BaseResponseDto {

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

    private List<AdminCourseListResponseDto> courses;
}
