package com.ju.islamicculturalcenter.dto.request.student.reviews;

import com.ju.islamicculturalcenter.dto.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateReviewRequest implements BaseRequestDto {

    private Integer rating;

    private String comment;
}
