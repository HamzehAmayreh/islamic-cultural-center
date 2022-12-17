package com.ju.islamicculturalcenter.restcontrollers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminInstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminInstructorUpdateRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminResetInstructorPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.dto.response.admin.instructor.AdminInstructorResponseDto;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminInstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/instructors")
public class AdminInstructorController {

    private final AdminInstructorService adminInstructorService;

    public AdminInstructorController(AdminInstructorService adminInstructorService) {
        this.adminInstructorService = adminInstructorService;
    }

    @PostMapping
    public ResponseEntity<Response<AdminInstructorResponseDto>> createInstructor(@RequestBody AdminInstructorRequestDto requestDto) {
        Response<AdminInstructorResponseDto> response = Response.<AdminInstructorResponseDto>builder()
                .data(adminInstructorService.save(requestDto))
                .code(CODE.CREATED.getId())
                .message(CODE.CREATED.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<List<AdminInstructorResponseDto>>> listInstructors() {
        Response<List<AdminInstructorResponseDto>> response = Response.<List<AdminInstructorResponseDto>>builder()
                .data(adminInstructorService.findAllByActive(true))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<AdminInstructorResponseDto>> viewInstructorProfile(@PathVariable Long id) {
        Response<AdminInstructorResponseDto> response = Response.<AdminInstructorResponseDto>builder()
                .data(adminInstructorService.findById(id, true))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<AdminInstructorResponseDto>> updateInstructor(@PathVariable Long id, @RequestBody AdminInstructorUpdateRequestDto updateDto) {
        Response<AdminInstructorResponseDto> response = Response.<AdminInstructorResponseDto>builder()
                .data(adminInstructorService.update(id, updateDto))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteInstructor(@PathVariable Long id) {
        adminInstructorService.deleteById(id);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<Response<Void>> changeInstructorPassword(@RequestBody AdminResetInstructorPasswordRequestDto requestDto) {
        adminInstructorService.resetPassword(requestDto);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
