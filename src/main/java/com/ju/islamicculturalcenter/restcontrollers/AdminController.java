package com.ju.islamicculturalcenter.restcontrollers;

import com.ju.islamicculturalcenter.dto.request.admin.AdminRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminUpdatePasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminUpdateRequestDto;
import com.ju.islamicculturalcenter.dto.response.admin.AdminResponseDto;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createAdmin(@RequestBody AdminRequestDto requestDto) { //TODO ONLY SUPER ADMINS CAN CREATE
        adminService.save(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AdminResponseDto>> viewAllActiveAdmins() {
        return new ResponseEntity<>(adminService.findAllByActive(true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<AdminResponseDto> viewProfile(@PathVariable Long id) {
        return new ResponseEntity<>(adminService.findById(id, true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<AdminResponseDto> updateProfile(@PathVariable Long id, @RequestBody AdminUpdateRequestDto requestDto) {
        return new ResponseEntity<>(adminService.update(id, requestDto), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) { //TODO ONLY SUPER ADMINS CAN DELETE
        adminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/reset")
    public ResponseEntity<Void> resetPassword(@RequestBody AdminResetPasswordRequestDto requestDto) {
        adminService.resetPassword(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/updatePassword")
    public ResponseEntity<Void> updatePassword(@RequestBody AdminUpdatePasswordRequestDto requestDto) {
        adminService.updatePassword(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
