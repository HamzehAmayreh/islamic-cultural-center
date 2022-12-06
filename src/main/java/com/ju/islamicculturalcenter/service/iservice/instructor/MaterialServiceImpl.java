package com.ju.islamicculturalcenter.service.iservice.instructor;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorMaterialDeleteRequest;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorMaterialRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorMaterialUpdateRequestDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorMaterialResponseDto;
import com.ju.islamicculturalcenter.entity.CourseEntity;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.entity.MaterialEntity;
import com.ju.islamicculturalcenter.exceptions.NotFoundException;
import com.ju.islamicculturalcenter.repos.CourseRepo;
import com.ju.islamicculturalcenter.repos.MaterialRepo;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.stream.Collectors;

public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepo materialRepo;
    private final CourseRepo courseRepo;

    public MaterialServiceImpl(MaterialRepo materialRepo, CourseRepo courseRepo) {
        this.materialRepo = materialRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public InstructorMaterialResponseDto addMaterialToCourse(InstructorMaterialRequestDto request) {
        CourseEntity course = courseRepo.findById(request.getCourseId())
                .orElseThrow(() -> new NotFoundException("No course was found with Id: {" + request.getCourseId() + "}"));

        MaterialEntity entity = MaterialEntity.builder()
                .course(course)
                .url(request.getUrl())
                .year(request.getYear())
                .active(true)
                .instructor(InstructorEntity.builder().id(UserDetailsUtil.userDetails().getId()).build())
                .build();

        materialRepo.save(entity);

        return InstructorMaterialResponseDto.builder()
                .id(entity.getId())
                .courseId(entity.getCourse().getId())
                .url(entity.getUrl())
                .year(entity.getYear())
                .isActive(entity.getIsActive())
                .build();
    }

    @Override
    public List<InstructorMaterialResponseDto> viewCourseMaterials(Long courseId) {
        List<MaterialEntity> entities = materialRepo.findAllByCourseId(courseId);

        return entities.stream().map(r ->
                InstructorMaterialResponseDto.builder()
                        .id(r.getId())
                        .url(r.getUrl())
                        .year(r.getYear())
                        .isActive(r.getIsActive())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public void deleteMaterial(InstructorMaterialDeleteRequest request) {
        MaterialEntity material = materialRepo.findByIdAndCourseId(request.getMaterialId(), request.getCourseId());
        materialRepo.delete(material);
    }

    @Override
    public void updateMaterial(Long materialId, InstructorMaterialUpdateRequestDto request) {
        MaterialEntity material = materialRepo.findById(materialId)
                .orElseThrow(() -> new NotFoundException("No material was found with Id: {" + materialId +"}"));

        material.setUrl(request.getUrl());
        material.setYear(request.getYear());
        material.setIsActive(request.getIsActive());

        materialRepo.save(material);
    }
}
