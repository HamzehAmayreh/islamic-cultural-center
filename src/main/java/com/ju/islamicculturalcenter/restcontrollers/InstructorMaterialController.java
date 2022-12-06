package com.ju.islamicculturalcenter.restcontrollers;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorMaterialDeleteRequest;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorMaterialRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorMaterialUpdateRequestDto;
import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorMaterialResponseDto;
import com.ju.islamicculturalcenter.service.iservice.instructor.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructor/materials")
public class InstructorMaterialController {
    private final MaterialService materialService;

    public InstructorMaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<Response<InstructorMaterialResponseDto>> save(@RequestBody InstructorMaterialRequestDto requestDto) {
        Response<InstructorMaterialResponseDto> response = Response.<InstructorMaterialResponseDto>builder()
                .success(true)
                .data(materialService.addMaterialToCourse(requestDto))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course-material/{courseId}")
    public ResponseEntity<Response<List<InstructorMaterialResponseDto>>> getMaterialsByCourseId(@PathVariable Long courseId) {
        Response<List<InstructorMaterialResponseDto>> response = Response.<List<InstructorMaterialResponseDto>>builder()
                .success(true)
                .data(materialService.viewCourseMaterials(courseId))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Response<Void>> deleteMaterial(@RequestBody InstructorMaterialDeleteRequest request) {
        materialService.deleteMaterial(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<Response<Void>> updateMaterial(@PathVariable Long materialId, @RequestBody InstructorMaterialUpdateRequestDto requestDto) {
        materialService.updateMaterial(materialId, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
