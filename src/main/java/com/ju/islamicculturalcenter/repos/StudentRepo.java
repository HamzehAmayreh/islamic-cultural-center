package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends BaseRepo<StudentEntity, Long> {
    Optional<StudentEntity> findByEmail(String email);
}
