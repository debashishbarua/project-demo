package com.amdocs.dao;

import java.sql.SQLException;
import java.util.List;

import com.amdocs.exception.StudentNotFoundException;
import com.amdocs.model.Student;

public interface StudentDao {
	
	List<Student> displayAllStudent() throws SQLException;
	
	Student findById(int studentId) throws StudentNotFoundException, SQLException; 
	
	boolean insert(Student student) throws SQLException;
}
