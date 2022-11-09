package com.ju.islamicculturalcenter.dto.response.admin;

import com.ju.islamicculturalcenter.dto.BaseAdminResponse;
import com.ju.islamicculturalcenter.dto.BaseResponseDto;
import com.ju.islamicculturalcenter.entity.PositionEntity;
import lombok.Builder;

import java.sql.Timestamp;
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

    @Builder
    public AdminInstructorResponseDto(Long id, Long createdById, Long updatedById, Timestamp creationDate, Timestamp updateDate, Boolean isActive, String firstName, String lastName, String userName, String email, Integer phoneNumber, String facebookUrl, PositionEntity position, String imageUrl, String isVolunteer, Double salary, String cvUrl, String subNumber, List<AdminCourseListResponseDto> courses) {
        super(id, createdById, updatedById, creationDate, updateDate, isActive);
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
        this.courses = courses;
    }
}
