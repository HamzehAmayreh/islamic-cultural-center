package com.ju.islamicculturalcenter.service.iservice.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorMaterialDeleteRequest;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorMaterialRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorMaterialUpdateRequestDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorMaterialResponseDto;

import java.util.List;

public interface MaterialService{
    InstructorMaterialResponseDto addMaterialToCourse(InstructorMaterialRequestDto request);

    List<InstructorMaterialResponseDto> viewCourseMaterials(Long courseId);

    void deleteMaterial(InstructorMaterialDeleteRequest request);

    void updateMaterial(Long materialId, InstructorMaterialUpdateRequestDto request);
}
