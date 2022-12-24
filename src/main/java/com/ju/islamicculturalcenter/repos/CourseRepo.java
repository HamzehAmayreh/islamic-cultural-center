package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.CourseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends BaseRepo<CourseEntity, Long> {

    List<CourseEntity> findAllByNameLike(String name);

    Optional<CourseEntity> findByName(String name);
}
