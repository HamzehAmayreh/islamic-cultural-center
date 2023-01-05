package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ReviewRepo extends BaseRepo<ReviewEntity, Long>{

    @Query("select r from ReviewEntity r ORDER BY r.rating desc")
    List<ReviewEntity> findTopRating(Pageable pageable);

    @Query("SELECT AVG(r.rating) from ReviewEntity r where r.isActive = true")
    Double findAverage();

    Long countAllByIsActiveAndCreationDateGreaterThanEqual(Boolean isActive, Timestamp creationDate);
}
