package com.ju.islamicculturalcenter.restcontrollers;

import com.ju.islamicculturalcenter.dto.request.admin.AdminResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.instructor.InstructorUpdateDto;
import com.ju.islamicculturalcenter.dto.response.instructor.InstructorResponseDto;
import com.ju.islamicculturalcenter.entity.InstructorEntity;
import com.ju.islamicculturalcenter.service.impl.instructor.InstructorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/Instructor")
public class InstructorRestController {

    private InstructorServiceImpl instructorService;

    public InstructorRestController(InstructorServiceImpl instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/{instructorId}")
    public InstructorResponseDto getInstructor(@PathVariable Long instructorId) {
        return instructorService.findById(instructorId, true);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<Void> updateInstructorPassword(@RequestBody InstructorUpdateDto instructor) {

        instructorService.updatePassword(instructor);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/reset")
    public ResponseEntity<Void> resetInstructorPassword(@RequestBody InstructorResetPasswordRequestDto requestDto) {
        instructorService.resetPassword(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateInfo/{instructorId}")
    public ResponseEntity<Void> updateInstructor(@RequestBody InstructorUpdateDto instructor, @PathVariable Long instructorId) {

        instructorService.updateInstructor(instructor, instructorId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
