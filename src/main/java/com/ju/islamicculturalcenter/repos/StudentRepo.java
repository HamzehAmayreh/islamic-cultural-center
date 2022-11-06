package com.ju.islamicculturalcenter.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ju.islamicculturalcenter.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
