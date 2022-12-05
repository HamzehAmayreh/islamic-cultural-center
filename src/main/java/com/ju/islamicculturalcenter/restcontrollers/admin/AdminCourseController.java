package com.ju.islamicculturalcenter.restcontrollers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.course.AdminCourseRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.course.AdminUpdateCourseRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.course.AdminCourseResponseDto;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminCourseService;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminInstructorCourseService;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStudentCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/courses")
public class AdminCourseController {

    private final AdminCourseService adminCourseService;
    private final AdminStudentCourseService adminStudentCourseService;
    private final AdminInstructorCourseService adminInstructorCourseService;

    public AdminCourseController(AdminCourseService adminCourseService, AdminStudentCourseService adminStudentCourseService, AdminInstructorCourseService adminInstructorCourseService) {
        this.adminCourseService = adminCourseService;
        this.adminStudentCourseService = adminStudentCourseService;
        this.adminInstructorCourseService = adminInstructorCourseService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AdminCourseResponseDto> createCourse(@RequestBody AdminCourseRequestDto requestDto) {
        return new ResponseEntity<>(adminCourseService.save(requestDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AdminCourseResponseDto>> viewAllActiveCourses() {
        return new ResponseEntity<>(adminCourseService.findAllByActive(true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<AdminCourseResponseDto> viewCourse(@PathVariable Long id) {
        return new ResponseEntity<>(adminCourseService.findById(id, true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<AdminCourseResponseDto> updateProfile(@PathVariable Long id, @RequestBody AdminUpdateCourseRequestDto requestDto) {
        return new ResponseEntity<>(adminCourseService.update(id, requestDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminCourseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
