package com.ju.islamicculturalcenter.service.iservice;

import java.util.List;

import com.ju.islamicculturalcenter.entity.Student;

public interface StudentService {

	public List<Student> findAll();
	
	public Student findById(Long theId);
	
	public void save(Student theStudent);
	
	public void deleteById(Long theId);
	


}

