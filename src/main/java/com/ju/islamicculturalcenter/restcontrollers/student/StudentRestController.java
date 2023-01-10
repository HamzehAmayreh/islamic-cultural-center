package com.ju.islamicculturalcenter.restcontrollers.student;

import com.ju.islamicculturalcenter.dto.request.student.course.StudentCourseRequestDto;
import com.ju.islamicculturalcenter.dto.request.student.profile.StudentSignUpRequestDto;
import com.ju.islamicculturalcenter.dto.request.student.profile.StudentUpdatePasswordRequest;
import com.ju.islamicculturalcenter.dto.request.student.profile.StudentUpdateProfileRequest;
import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.dto.response.ResponseList;
import com.ju.islamicculturalcenter.dto.response.student.course.StudentCourseResponse;
import com.ju.islamicculturalcenter.dto.response.student.profile.StudentProfileResponse;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import com.ju.islamicculturalcenter.service.iservice.student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student/students")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Response<StudentProfileResponse>> signup(@RequestBody StudentSignUpRequestDto requestDto) {

        Response<StudentProfileResponse> response = Response.<StudentProfileResponse>builder()
                .data(studentService.save(requestDto))
                .message(CODE.CREATED.name())
                .code(CODE.CREATED.getId())
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<Response<StudentProfileResponse>> viewProfile() {

        Response<StudentProfileResponse> response = Response.<StudentProfileResponse>builder()
                .data(studentService.viewProfile())
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<Response<StudentProfileResponse>> updateProfile(@RequestBody StudentUpdateProfileRequest request) {

        Response<StudentProfileResponse> response = Response.<StudentProfileResponse>builder()
                .data(studentService.update(UserDetailsUtil.userDetails().getId(), request))
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<Response<StudentProfileResponse>> updatePassword(@RequestBody StudentUpdatePasswordRequest request) {

        studentService.updateOwnPassword(request);

        Response<StudentProfileResponse> response = Response.<StudentProfileResponse>builder()
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<Response<List<StudentCourseResponse>>> viewCourses(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                             @RequestParam(required = false, defaultValue = "20") Integer size) {

        ResponseList<StudentCourseResponse> responseList = studentService.viewAvailableCourses(page, size);

        Response<List<StudentCourseResponse>> response = Response.<List<StudentCourseResponse>>builder()
                .data(responseList.getData())
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .allRecords(responseList.getTotalElements())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Response<StudentCourseResponse>> viewCourseDetails(@PathVariable Long id) {

        Response<StudentCourseResponse> response = Response.<StudentCourseResponse>builder()
                .data(studentService.viewCourseDetails(id))
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/my-courses/{id}")
    public ResponseEntity<Response<StudentCourseResponse>> viewRegisteredCourseDetails(@PathVariable Long id) {

        Response<StudentCourseResponse> response = Response.<StudentCourseResponse>builder()
                .data(studentService.viewRegisteredCourseDetails(id))
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<Response<List<StudentCourseResponse>>> registerToCourse(@RequestBody StudentCourseRequestDto requestDto) {
        studentService.registerToCourse(requestDto);

        Response<List<StudentCourseResponse>> response = Response.<List<StudentCourseResponse>>builder()
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/my-courses")
    public ResponseEntity<Response<List<StudentCourseResponse>>> viewMyCourses(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                               @RequestParam(required = false, defaultValue = "20") Integer size) {

        ResponseList<StudentCourseResponse> responseList = studentService.viewRegisteredCourses(page, size);

        Response<List<StudentCourseResponse>> response = Response.<List<StudentCourseResponse>>builder()
                .data(responseList.getData())
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .allRecords(responseList.getTotalElements())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
