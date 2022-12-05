package com.ju.islamicculturalcenter.restcontrollers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.student.AdminResetStudentPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.student.AdminStudentRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.student.AdminUpdateStudentRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminStudentResponseDto;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/students")
public class AdminStudentController {

    private final AdminStudentService adminStudentService;

    public AdminStudentController(AdminStudentService adminStudentService) {
        this.adminStudentService = adminStudentService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AdminStudentResponseDto> createInstructor(@RequestBody AdminStudentRequestDto requestDto) {
        return new ResponseEntity<>(adminStudentService.save(requestDto), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AdminStudentResponseDto>> listInstructors() {
        return new ResponseEntity<>(adminStudentService.findAllByActive(true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<AdminStudentResponseDto> viewInstructorProfile(@PathVariable Long id) {
        return new ResponseEntity<>(adminStudentService.findById(id, true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<AdminStudentResponseDto> updateInstructor(@PathVariable Long id, @RequestBody AdminUpdateStudentRequestDto updateDto) {
        return new ResponseEntity<>(adminStudentService.update(id, updateDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        adminStudentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/password")
    public ResponseEntity<Void> changeInstructorPassword(@RequestBody AdminResetStudentPasswordRequestDto requestDto) {
        adminStudentService.resetPassword(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
