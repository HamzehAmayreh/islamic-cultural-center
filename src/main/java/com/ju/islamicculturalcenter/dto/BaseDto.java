package com.ju.islamicculturalcenter.dto;

import java.sql.Timestamp;

public class BaseDto {

    protected Long createdBy;

    protected Long editedBy;
    
    protected Timestamp creationDate;

    protected Timestamp editedDate;

    protected Boolean isActive;
}
