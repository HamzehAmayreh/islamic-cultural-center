package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.InstructorEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepo extends BaseRepo<InstructorEntity, Long> {
    public InstructorEntity findInstructorEntityById(Long id);

    Optional<InstructorEntity> findByEmail(String email);
}
