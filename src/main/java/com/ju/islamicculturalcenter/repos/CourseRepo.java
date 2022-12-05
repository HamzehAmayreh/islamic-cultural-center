package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.CourseEntity;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends BaseRepo<CourseEntity, Long> {

    List<CourseEntity> findAllByNameLike(String name);
}
