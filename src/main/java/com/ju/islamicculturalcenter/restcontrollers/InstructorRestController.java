package com.ju.islamicculturalcenter.restcontrollers;

import com.ju.islamicculturalcenter.dto.request.instructor.InstructorResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdatePassword;
import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorCourseResponseDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.service.auth.UserDetailsUtil;
import com.ju.islamicculturalcenter.service.iservice.instructor.InstructorCoursesService;
import com.ju.islamicculturalcenter.service.iservice.instructor.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructor/instructors")
public class InstructorRestController {

    private final InstructorService instructorService;
    private final InstructorCoursesService instructorCoursesService;

    public InstructorRestController(InstructorService instructorService, InstructorCoursesService instructorCoursesService) {
        this.instructorService = instructorService;
        this.instructorCoursesService = instructorCoursesService;
    }

    @GetMapping("/{instructorId}")
    public InstructorResponseDto getInstructor(@PathVariable Long instructorId) {
        return instructorService.findById(instructorId, true);
    }

    @GetMapping("/profile")
    public ResponseEntity<Response<InstructorResponseDto>> viewProfile(){
        Response<InstructorResponseDto> response= Response.<InstructorResponseDto>builder()
                .success(true)
                //.data(instructorService.viewProfile(UserDetailsUtil.userDetails().getId()))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updateInstructorPassword(@RequestBody InstructorUpdatePassword request) {

        instructorService.updatePassword(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/reset")
    public ResponseEntity<Void> resetInstructorPassword(@RequestBody InstructorResetPasswordRequestDto requestDto) {
        instructorService.resetPassword(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{instructorId}")
    public ResponseEntity<Void> updateInstructor(@RequestBody InstructorUpdateDto request, @PathVariable Long instructorId) {

        instructorService.updateInstructor(request, instructorId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/my-courses")
    public ResponseEntity<Response<List<InstructorCourseResponseDto>>> viewMyCourses(){
        Response<List<InstructorCourseResponseDto>> response= Response.<List<InstructorCourseResponseDto>>builder()
                .success(true)
                //.data(instructorCoursesService.myCourses(UserDetailsUtil.userDetails().getId()))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-students")
    public ResponseEntity<Response<List<InstructorCourseResponseDto>>> viewMyStudents(){
        Response<List<InstructorCourseResponseDto>> response= Response.<List<InstructorCourseResponseDto>>builder()
                .success(true)
                //.data(instructorCoursesService.myStudents(UserDetailsUtil.userDetails().getId()))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search-courses")
    public ResponseEntity<Response<List<InstructorCourseResponseDto>>> searchCourse(@RequestParam String name){
        Response<List<InstructorCourseResponseDto>> response= Response.<List<InstructorCourseResponseDto>>builder()
                .success(true)
                .data(instructorCoursesService.searchCourseByName(name))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();
        return ResponseEntity.ok(response);
    }
}
