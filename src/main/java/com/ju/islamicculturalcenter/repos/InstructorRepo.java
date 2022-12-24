package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.InstructorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepo extends BaseRepo<InstructorEntity, Long> {

    InstructorEntity findInstructorEntityById(Long id);

    List<InstructorEntity> findAllByUser_FirstNameOrUser_LastNameLike(String name);
}
