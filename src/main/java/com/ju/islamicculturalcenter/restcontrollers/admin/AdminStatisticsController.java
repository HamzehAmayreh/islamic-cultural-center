package com.ju.islamicculturalcenter.restcontrollers.admin;

import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.dto.response.admin.StatisticsResponse;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminStatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/admin/stats")
public class AdminStatisticsController {

    private final AdminStatisticsService adminStatisticsService;

    public AdminStatisticsController(AdminStatisticsService adminStatisticsService) {
        this.adminStatisticsService = adminStatisticsService;
    }

    @GetMapping
    public ResponseEntity<Response<StatisticsResponse>> getAllStats(@RequestParam(required = false)
                                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){

        Response<StatisticsResponse> response = Response.<StatisticsResponse>builder()
                .data(adminStatisticsService.findAllAdminStatistics(date))
                .success(true)
                .code(CODE.OK.getId())
                .message(CODE.OK.name())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
