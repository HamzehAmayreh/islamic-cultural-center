package com.ju.islamicculturalcenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ju.islamicculturalcenter.entity.Student;
import com.ju.islamicculturalcenter.repos.StudentRepo;

@Service
public class StudentServiceImp implements StudentService {

	private StudentRepo studentRepo;
	
	@Autowired
	public StudentServiceImp(StudentRepo theStudentRepo) {
		studentRepo = theStudentRepo;
	}
	
	@Override
	public List<Student> findAll(){
	
		return studentRepo.findAll();
	}

	@Override
	public Student findById(int theId) {
		Student st = null;
		
		Optional <Student> res = studentRepo.findById(theId);
		if(res.isPresent()) {
			st = res.get();
		}
		else {
			throw new RuntimeException("Did not find Student id -"+ theId);
		}
		return st;
		
	}

	@Override	
	public void save(Student theStudent) {
		// TODO Auto-generated method stub
		studentRepo.save(theStudent);
		
	}

	@Override
	public void deleteById(int theId) {
		
		studentRepo.deleteById(theId);
		
	}
	
	
	
}
