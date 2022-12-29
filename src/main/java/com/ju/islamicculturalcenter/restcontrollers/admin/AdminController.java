package com.ju.islamicculturalcenter.restcontrollers.admin;

import com.ju.islamicculturalcenter.dto.request.admin.admin.AdminRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.admin.AdminResetPasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.admin.AdminUpdatePasswordRequestDto;
import com.ju.islamicculturalcenter.dto.request.admin.admin.AdminUpdateRequestDto;
import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.dto.response.ResponseList;
import com.ju.islamicculturalcenter.dto.response.admin.AdminRoleResponse;
import com.ju.islamicculturalcenter.dto.response.admin.admin.AdminResponseDto;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminRoleService;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/admins")
public class AdminController {

    private final AdminService adminService;
    private final AdminRoleService adminRoleService;

    public AdminController(AdminService adminService, AdminRoleService adminRoleService) {
        this.adminService = adminService;
        this.adminRoleService = adminRoleService;
    }

    @PostMapping
    public ResponseEntity<Response<AdminResponseDto>> createAdmin(@RequestBody AdminRequestDto requestDto) { //SUPER ADMINS
        Response<AdminResponseDto> response = Response.<AdminResponseDto>builder()
                .data(adminService.save(requestDto))
                .code(CODE.CREATED.getId())
                .message(CODE.CREATED.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<List<AdminResponseDto>>> viewAllActiveAdmins(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                                @RequestParam(required = false, defaultValue = "20") Integer size) {
        ResponseList<AdminResponseDto> active = adminService.findAllByActive(page, size, true);
        Response<List<AdminResponseDto>> response = Response.<List<AdminResponseDto>>builder()
                .data(active.getData())
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .allRecords(active.getTotalElements())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<AdminResponseDto>> viewDetails(@PathVariable Long id) {
        Response<AdminResponseDto> response = Response.<AdminResponseDto>builder()
                .data(adminService.findById(id, true))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<AdminResponseDto>> updateAdmin(@PathVariable Long id, @RequestBody AdminUpdateRequestDto requestDto) { //SUPER ADMINS
        Response<AdminResponseDto> response = Response.<AdminResponseDto>builder()
                .data(adminService.update(id, requestDto))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteAdmin(@PathVariable Long id) { //SUPER ADMINS
        adminService.deleteById(id);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<Response<Void>> resetPassword(@RequestBody AdminResetPasswordRequestDto requestDto) { //SUPER ADMINS
        adminService.resetPassword(requestDto);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update-password")
    public ResponseEntity<Response<Void>> updatePassword(@RequestBody AdminUpdatePasswordRequestDto requestDto) {
        adminService.updatePassword(requestDto);
        Response<Void> response = Response.<Void>builder()
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<Response<AdminResponseDto>> updateProfile(@RequestBody AdminUpdateRequestDto requestDto) {
        Response<AdminResponseDto> response = Response.<AdminResponseDto>builder()
                .data(adminService.updateOwnProfile(requestDto))
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Response<AdminResponseDto>> viewProfile() {
        Response<AdminResponseDto> response = Response.<AdminResponseDto>builder()
                .data(adminService.viewOwnProfile())
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Response<List<AdminResponseDto>>> searchByName(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                         @RequestParam(required = false, defaultValue = "20") Integer size,
                                                                         @RequestParam String keyword){
        ResponseList<AdminResponseDto> responseList = adminService.searchAdminByName(page, size, keyword);
        Response<List<AdminResponseDto>> response = Response.<List<AdminResponseDto>>builder()
                .data(responseList.getData())
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .allRecords(responseList.getTotalElements())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/roles/admin")
    public ResponseEntity<Response<List<AdminRoleResponse>>> getAllAdminRoles(){
        Response<List<AdminRoleResponse>> response = Response.<List<AdminRoleResponse>>builder()
                .data(adminRoleService.findAllAdminRoles())
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Response<List<AdminRoleResponse>>> getAllRoles(){
        Response<List<AdminRoleResponse>> response = Response.<List<AdminRoleResponse>>builder()
                .data(adminRoleService.findAllRoles())
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
