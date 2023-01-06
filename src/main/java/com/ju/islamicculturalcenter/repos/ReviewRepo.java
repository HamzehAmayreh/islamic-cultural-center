package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ReviewRepo extends BaseRepo<ReviewEntity, Long>{

    @Query("select r from ReviewEntity r ORDER BY r.creationDate desc")
    List<ReviewEntity> findTopRating(Pageable pageable);

    @Query("select r from ReviewEntity r where r.creationDate <:date ORDER BY r.creationDate desc")
    List<ReviewEntity> findTopRatingWithDate(Timestamp date, Pageable pageable);

    @Query("SELECT AVG(r.rating) from ReviewEntity r where r.isActive = true")
    Double findAverage();

    Long countAllByIsActiveAndCreationDateLessThanEqual(Boolean isActive, Timestamp creationDate);
}
