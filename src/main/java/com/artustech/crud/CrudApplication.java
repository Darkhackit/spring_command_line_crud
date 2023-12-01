package com.artustech.crud;

import com.artustech.crud.dao.StudentDAO;
import com.artustech.crud.entity.Student;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class CrudApplication {
	private Random random;

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO   studentDAO)
	{
		return runner -> {
//			createStudent(studentDAO);
//			createMultipleStudent(studentDAO);
//			readStudent(studentDAO);
//			queryForStudent(studentDAO);
//			queryForLastname(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
			deleteAllStudent(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		int numRows = studentDAO.deleteAll();
		System.out.println(numRows + " Deleted From database");
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int random = new Random().nextInt(20);
		Student student = studentDAO.findById(random);

		System.out.println("Deleting student : " + student);
		studentDAO.delete(random);

	}

	private void updateStudent(StudentDAO studentDAO) {
		int randomID = new Random().nextInt(20);
		Student student = studentDAO.findById(randomID);
		System.out.println(student);
		student.setLastName("Bakker");
		student.setFirstName("Jefferson ");
		studentDAO.update(student);

		System.out.println("Updated Student Lastname is:");

		System.out.println(student );

	}

	private void queryForLastname(StudentDAO studentDAO) {
		String lastName = "qHlNgejlFn";
		List<Student> students = studentDAO.findByLastName(lastName);

		for (Student student: students) {
			System.out.println(student + " ");
		}
	}

	private void queryForStudent(StudentDAO studentDAO) {

		List<Student> students = studentDAO.findAll();

//		System.out.println(students.toString() + " ");

		for (Student student: students) {
			System.out.println(student + " ");
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		int random = new Random().nextInt(20);

		Student student = studentDAO.findById(random);

		System.out.println(student);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {

		for (int i = 0; i < 10; i++) {
			String firstname = RandomStringUtils.randomAlphanumeric(10);
			String lastname = RandomStringUtils.randomAlphanumeric(10);

			String randomUsername = RandomStringUtils.randomAlphanumeric(8);
			String randomDomain = RandomStringUtils.randomAlphabetic(6) + ".com";

			String email = randomUsername + "@" + randomDomain;

			Student student = new Student(firstname,lastname,email);

			studentDAO.save(student);

		}
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating student");

		Student student = new Student("Emmanuel","Arthur","earthur510@gmail.com");

		studentDAO.save(student);

		System.out.println("Saved Student. Generated id: " + student.getId());

	}

}
