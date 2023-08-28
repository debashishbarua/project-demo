package com.amdocs;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.amdocs.dao.StudentDao;
import com.amdocs.dao.impl.StudentDaoImpl;
import com.amdocs.exception.StudentNotFoundException;
import com.amdocs.model.Student;

/**
 * Student App
 *
 */
public class StudentManagementApp {

	private static StudentDao dao = new StudentDaoImpl();

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			System.out.println("************Enter Your choice************");
			System.out.println("1. for Add to list");
			System.out.println("2. for delete to list");
			System.out.println("3. for Display All");
			System.out.println("4. for Find by id");
			System.out.println("20. for Stop");
			int ch = Integer.parseInt(scanner.nextLine());
			switch (ch) {
			case 1:
				System.out.println("****Adding Student Started***");
				addStudent();
				System.out.println("****Adding Student Ended***");
				break;
			case 2:
				System.out.println("****Deleting Student Started***");
				
				System.out.println("****Deleting Student Ended***");
				break;
			case 3:
				displayAllStudent();
				break;
			case 4:
				findById();
				break;
			case 20:
				System.exit(0);

			default:
				System.exit(0);
			}

		}

	}

	private static void addStudent() {
//		System.out.println("\nEnter Id:");
//		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("\nEnter First Name:");
		String firstName = scanner.nextLine();
		System.out.println("\nEnter Last Name:");
		String lastName = scanner.nextLine();
		System.out.println("\nEnter Address:");
		String address = scanner.nextLine();
		Student student = new Student(firstName, lastName, address);
		try {
			if(dao.insert(student)) {
				System.out.println("Inserted");
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}

	private static void findById() {
		System.out.println("\nEnter Id:");
		int studentId = Integer.parseInt(scanner.nextLine());
		try {
			Student findById = dao.findById(studentId);
			System.out.println(findById);
		} catch (StudentNotFoundException | SQLException e) {
			System.err.println(e);
		}
	}

	private static void displayAllStudent() {
		try {
			List<Student> displayAllStudent = dao.displayAllStudent();
			for (Student student : displayAllStudent) {
				System.out.println(student);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
}
