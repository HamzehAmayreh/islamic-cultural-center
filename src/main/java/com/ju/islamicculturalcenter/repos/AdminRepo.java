package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.AdminEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface AdminRepo extends BaseRepo<AdminEntity, Long> {

    @Query("Select a from AdminEntity a where a.user.firstName like %:name%")
    Page<AdminEntity> searchByName(String name, Pageable pageable);

    Long countAllByIsActiveAndCreationDateLessThanEqual(Boolean isActive, Timestamp creationDate);
}
