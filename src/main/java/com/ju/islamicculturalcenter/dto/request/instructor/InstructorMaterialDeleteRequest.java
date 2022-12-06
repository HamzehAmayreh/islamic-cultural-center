package com.ju.islamicculturalcenter.dto.request.instructor;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorMaterialDeleteRequest {
    private Long courseId;
    private Long materialId;
}
