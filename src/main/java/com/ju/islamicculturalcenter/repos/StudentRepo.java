package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends BaseRepo<StudentEntity, Long> {

    List<StudentEntity> findAllByUser_FirstNameOrUser_LastNameLike(String name);
}
