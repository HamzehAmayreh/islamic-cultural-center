package com.ju.islamicculturalcenter.restcontrollers;

import com.ju.islamicculturalcenter.dto.request.admin.AdminInstructorRequestDto;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminInstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AdminInstructorController {

    private final AdminInstructorService adminInstructorService;

    public AdminInstructorController(AdminInstructorService adminInstructorService) {
        this.adminInstructorService = adminInstructorService;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/createInstructor")
    public ResponseEntity<Void> createInstructor(@RequestBody AdminInstructorRequestDto requestDto) {
        adminInstructorService.save(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
