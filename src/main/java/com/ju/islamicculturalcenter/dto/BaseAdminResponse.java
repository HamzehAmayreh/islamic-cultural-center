package com.ju.islamicculturalcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseAdminResponse {

    private Long id;

    private Long createdById;

    private Long editedById;

    private Timestamp creationDate;

    private Timestamp editedDate;

    private Boolean isActive;
}
