package com.jacaranda.studentDb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.studentDb.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
}
