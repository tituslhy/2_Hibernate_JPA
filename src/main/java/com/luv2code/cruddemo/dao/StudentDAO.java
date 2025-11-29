package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    /**
     * C: Creates a new Student object
     * @param theStudent
     */
    void save(Student theStudent);

    /**
     * R1: Finds students by id. A read method
     * @param id
     * @return
     */
    Student findById(Integer id);

    /**
     * R2: Finds all students. A read method
     * @return
     */
    List<Student> findAll();

    /**
     * R3: Finds students by last name. A read method
     * @param theLastName
     * @return
     */
    List<Student> findByLastName(String theLastName);

    /**
     * U: Updates a student's particulars in the database. An update method.
     * @param theStudent
     */
    void update(Student theStudent);

}
