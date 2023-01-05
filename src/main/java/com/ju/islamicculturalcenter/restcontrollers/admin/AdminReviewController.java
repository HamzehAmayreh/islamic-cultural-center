package com.ju.islamicculturalcenter.restcontrollers.admin;

import com.ju.islamicculturalcenter.dto.response.CODE;
import com.ju.islamicculturalcenter.dto.response.Response;
import com.ju.islamicculturalcenter.dto.response.ResponseList;
import com.ju.islamicculturalcenter.dto.response.student.review.ReviewResponse;
import com.ju.islamicculturalcenter.service.iservice.admin.AdminReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/reviews")
public class AdminReviewController {

    private final AdminReviewsService adminReviewsService;

    public AdminReviewController(AdminReviewsService adminReviewsService) {
        this.adminReviewsService = adminReviewsService;
    }

    @GetMapping
    public ResponseEntity<Response<List<ReviewResponse>>> getAllReviews(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                        @RequestParam(required = false, defaultValue = "20") Integer size) {

        ResponseList<ReviewResponse> responseList = adminReviewsService.findAllReviews(page, size);

        Response<List<ReviewResponse>> response = Response.<List<ReviewResponse>>builder()
                .data(responseList.getData())
                .message(CODE.OK.name())
                .code(CODE.OK.getId())
                .success(true)
                .allRecords(responseList.getTotalElements())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
