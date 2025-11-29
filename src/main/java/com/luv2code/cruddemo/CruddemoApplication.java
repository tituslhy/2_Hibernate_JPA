package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    /**
     * Used for command line applications using Spring boot
     * Will be executed after the Spring Beans have been loaded
     * @return
     */
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner -> {
            // createStudent(studentDAO);
            // createMultipleStudents(studentDAO);
            // readStudent(studentDAO);
            // queryForStudents(studentDAO);
            queryForStudentsByLastName(studentDAO, "Doe");
            // updateStudent(studentDAO);
        };
    }

    private void updateStudent(StudentDAO studentDAO) {
        //retrieve student based on id:
        int studentId = 1;
        System.out.println("Retrieving student id: " + studentId);
        Student myStudent = studentDAO.findById(studentId);

        //updating student
        System.out.println("Updating student..");
        myStudent.setFirstName("Scooby");
        studentDAO.update(myStudent);

        System.out.println("Student updated. Student particulars: " + myStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO, String lastName) {
        List<Student> students = studentDAO.findByLastName(lastName);
        students.forEach(System.out::println);
    }

    private void queryForStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();
        students.forEach(System.out::println);
    }

    private void readStudent(StudentDAO studentDAO) {

        // Create a new student
        System.out.println("Creating a new student object...");
        Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

        // Save the student
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        // Print the student Id generated
        int theId = tempStudent.getId();
        System.out.println("Saved student. Generated id: " + theId);

        // Retrieve the student from the database
        System.out.println("Retrieving the student with the ID: " + theId);
        Student myStudent = studentDAO.findById(theId);

        // Display student
        System.out.println("Found the student: "+ myStudent);
    }

    /**
     * Test auto-increment in the database
     * @param studentDAO
     */
    private void createMultipleStudents(StudentDAO studentDAO) {
        System.out.println("Creating 3 student objects...");
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Doe", "mary@luv2code.com");
        Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

        System.out.println("Saving students...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);

    }

    /**
     * Creates and saves the student object. Displays id of saved student
     * @param studentDAO
     */
    private void createStudent(StudentDAO studentDAO) {
        System.out.println("Creating a new student object...");
        Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }

}
