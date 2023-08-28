package com.amdocs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.dao.StudentDao;
import com.amdocs.exception.StudentNotFoundException;
import com.amdocs.model.Student;
import com.amdocs.util.DbUtil;

public class StudentDaoImpl implements StudentDao {
	private final static String SELECT_ALL = "SELECT * FROM STUDENT";
	private final static String SELECT_BY_ID = "SELECT * FROM STUDENT WHERE id=?";
	private final static String INSERT = "insert into student(first_name,last_name,address) values(?,?,?)";
	private Connection connection = DbUtil.getConnection();

	@Override
	public List<Student> displayAllStudent() throws SQLException {
		List<Student> students = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL);
		while (rs.next()) {
			Student student = new Student();
			// Reading the data from the row and seting the data to student object
			student.setId(rs.getInt("id"));
			student.setFirstName(rs.getString("first_name"));
			student.setLastName(rs.getString("last_name"));
			student.setAddress(rs.getString("address"));
			// Adding to the list
			students.add(student);
		}
		rs.close();
		stmt.close();
		return students;
	}

	@Override
	public Student findById(int studentId) throws StudentNotFoundException, SQLException {
		Student student =null;
		PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
		stmt.setInt(1, studentId);
		ResultSet rs = stmt.executeQuery();
		System.out.println("Rs  : "  + rs);
		if(rs.next()) {
			student = new Student();
			// Reading the data from the row and seting the data to student object
			student.setId(rs.getInt("id"));
			student.setFirstName(rs.getString("first_name"));
			student.setLastName(rs.getString("last_name"));
			student.setAddress(rs.getString("address"));			
		}else {
			throw new StudentNotFoundException("Student Not Found With Id: " + studentId);
		}
		rs.close();
		stmt.close();
		return student;
		
	}

	@Override
	public boolean insert(Student student) throws SQLException {
		boolean result=false;
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setString(1,student.getFirstName() );
		stmt.setString(2,student.getLastName() );
		stmt.setString(3,student.getAddress() );
		if(stmt.executeUpdate()>0) {
			result = true;
		}
		stmt.close();
		return result;
	}

}
