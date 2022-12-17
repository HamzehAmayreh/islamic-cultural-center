package com.ju.islamicculturalcenter.restcontrollers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.course.AdminCourseRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.course.AdminUpdateCourseRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.instructorcourse.AdminInstructorCourseRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.studentcourse.AdminStudentCourseRequestDto;
import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.dto.response.admin.course.AdminCourseResponseDto;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminCourseService;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminInstructorCourseService;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStudentCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/courses")
public class AdminCourseController {

    private final AdminCourseService adminCourseService;
    private final AdminStudentCourseService adminStudentCourseService;
    private final AdminInstructorCourseService adminInstructorCourseService;

    public AdminCourseController(AdminCourseService adminCourseService, AdminStudentCourseService adminStudentCourseService, AdminInstructorCourseService adminInstructorCourseService) {
        this.adminCourseService = adminCourseService;
        this.adminStudentCourseService = adminStudentCourseService;
        this.adminInstructorCourseService = adminInstructorCourseService;
    }

    @PostMapping
    public ResponseEntity<Response<AdminCourseResponseDto>> createCourse(@RequestBody AdminCourseRequestDto requestDto) {
        Response<AdminCourseResponseDto> response = Response.<AdminCourseResponseDto>builder()
                .data(adminCourseService.save(requestDto))
                .code(CODE.CREATED.getId())
                .message(CODE.CREATED.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Response<List<AdminCourseResponseDto>>> viewAllActiveCourses() {
        Response<List<AdminCourseResponseDto>> response = Response.<List<AdminCourseResponseDto>>builder()
                .data(adminCourseService.findAllByActive(true))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<AdminCourseResponseDto>> viewCourse(@PathVariable Long id) {
        Response<AdminCourseResponseDto> response = Response.<AdminCourseResponseDto>builder()
                .data(adminCourseService.findById(id, true))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<AdminCourseResponseDto>> updateProfile(@PathVariable Long id, @RequestBody AdminUpdateCourseRequestDto requestDto){
        Response<AdminCourseResponseDto> response = Response.<AdminCourseResponseDto>builder()
                .data(adminCourseService.update(id, requestDto))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping( "/{id}")
    public ResponseEntity<Response<Void>> deleteAdmin(@PathVariable Long id) {
        adminCourseService.deleteById(id);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping( "/instructor")
    public ResponseEntity<Response<Void>> assignInstructorToCourse(@RequestBody AdminInstructorCourseRequestDto requestDto) {
        adminInstructorCourseService.assignInstructorToCourse(requestDto);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping( "/instructor")
    public ResponseEntity<Response<Void>> unAssignInstructorToCourse(@RequestBody AdminInstructorCourseRequestDto requestDto) {
        adminInstructorCourseService.unAssignInstructorToCourse(requestDto);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping( "/student")
    public ResponseEntity<Response<Void>> assignStudentToCourse(@RequestBody AdminStudentCourseRequestDto requestDto) {
        adminStudentCourseService.assignStudentToCourse(requestDto);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping( "/student")
    public ResponseEntity<Response<Void>> unAssignStudentToCourse(@RequestBody AdminStudentCourseRequestDto requestDto) {
        adminStudentCourseService.unAssignStudentToCourse(requestDto);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
