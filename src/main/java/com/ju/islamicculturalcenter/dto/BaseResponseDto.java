package com.ju.islamicculturalcenter.dto;


import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BaseResponseDto {

    private Long id;

    private Long createdById;

    private Long editedById;

    private Timestamp creationDate;

    private Timestamp editedDate;

    private Boolean isActive;
}
