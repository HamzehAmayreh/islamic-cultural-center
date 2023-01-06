package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.StudentCoursesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StudentCoursesRepo extends BaseRepo<StudentCoursesEntity, Long> {

    List<StudentCoursesEntity> findAllByCourseId(Long courseId);

    @Query("select count(distinct student) from StudentCoursesEntity where isActive = true")
    Long findAssignedStudents();

    @Query("select count(distinct student) from StudentCoursesEntity where isActive = true and creationDate < :date")
    Long findAssignedStudentsWithDate(Timestamp date);
}
