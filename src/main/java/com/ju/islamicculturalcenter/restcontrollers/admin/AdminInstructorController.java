package com.ju.islamicculturalcenter.restcontrollers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminInstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminInstructorUpdateRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.instructor.AdminResetInstructorPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.instructor.AdminInstructorResponseDto;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminInstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/instructors")
public class AdminInstructorController {

    private final AdminInstructorService adminInstructorService;

    public AdminInstructorController(AdminInstructorService adminInstructorService) {
        this.adminInstructorService = adminInstructorService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AdminInstructorResponseDto> createInstructor(@RequestBody AdminInstructorRequestDto requestDto) {
        return new ResponseEntity<>(adminInstructorService.save(requestDto), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AdminInstructorResponseDto>> listInstructors() {
        return new ResponseEntity<>
                (adminInstructorService.findAllByActive(true),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<AdminInstructorResponseDto> viewInstructorProfile(@PathVariable Long id) {
        return new ResponseEntity<>
                (adminInstructorService.findById(id, true),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<AdminInstructorResponseDto> updateInstructor(@PathVariable Long id, @RequestBody AdminInstructorUpdateRequestDto updateDto) {
        return new ResponseEntity<>
                (adminInstructorService.update(id, updateDto),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        adminInstructorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/password")
    public ResponseEntity<Void> changeInstructorPassword(@RequestBody AdminResetInstructorPasswordRequestDto requestDto) {
        adminInstructorService.resetPassword(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
