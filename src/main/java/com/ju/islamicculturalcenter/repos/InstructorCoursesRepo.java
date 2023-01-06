package com.ju.islamicculturalcenter.repos;

import com.ju.islamicculturalcenter.entity.InstructorCoursesEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface InstructorCoursesRepo extends BaseRepo<InstructorCoursesEntity, Long> {

    List<InstructorCoursesEntity> findAllByInstructor_User_IdAndCourse_NameLike(Long instructorId, String name);

    @Query("select count(distinct instructor) from InstructorCoursesEntity where isActive = true")
    Long findAssignedInstructors();

    @Query("select count(distinct instructor) from InstructorCoursesEntity where isActive = true and creationDate < :date")
    Long findAssignedInstructorsWithDate(Timestamp date);
}
