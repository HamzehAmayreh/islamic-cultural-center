package com.ju.islamicculturalcenter.restcontrollers;

import com.ju.islamicculturalcenter.dto.request.admin.AdminRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.AdminResetPasswordRequestDto;
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
    public ResponseEntity<Void> createAdmin(@RequestBody AdminRequestDto requestDto){
        adminService.save(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AdminResponseDto>> viewAllActiveAdmins(){
        return new ResponseEntity<>(adminService.findAllByActive(true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<AdminResponseDto> viewProfile(@PathVariable Long id){
        return new ResponseEntity<>(adminService.findById(id, true), HttpStatus.OK);
    }

//    public ResponseEntity<AdminResponseDto> deleteAdmin(@PathVariable Long id){//TODO ONLY SUPER ADMINS CAN DELETE
//        return new ResponseEntity<>(adminService.findById(id, true), HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/reset")
    public ResponseEntity<Void> resetPassword(@RequestBody AdminResetPasswordRequestDto requestDto){
        adminService.resetPassword(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
