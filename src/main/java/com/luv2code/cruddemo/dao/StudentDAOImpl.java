package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Implements save method
     * "@Transactional" is required because we are updating
     * the database, not reading from it.
     * @param theStudent
     */
    @Override
    @Transactional //use Spring's! Not JPA's!
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    /**
     * Implements the findById method
     * @param id
     * @return
     */
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    /**
     * USING CREATE QUERY
     * Gets all students ordered by last name in ascending order
     * Use the actual Entity name, i.e. "Student"
     * "lastName" is the field of the JPA entity, not
     * the database column
     * @return
     */
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student ORDER BY lastName ASC", Student.class);
        return theQuery.getResultList();
    }

    /**
     * :=theData is a placeholder to be filled in
     * @param theLastName
     * @return
     */
    @Override
    public List<Student> findByLastName(String theLastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student where lastName=:theData", Student.class);

        //set parameter
        theQuery.setParameter("theData", theLastName);

        //return results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    /**
     * Delete using entityManager.remove on a Student object
     * @param id
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        Student theStudent = entityManager.find(Student.class, id);
        entityManager.remove(theStudent);
    }

    /**
     * You can implement delete by using createQuery method as well.
     * @return
     */
    @Override
    @Transactional
    public int deleteAll() {
        return entityManager.createQuery(
                "DELETE FROM Student"
        ).executeUpdate();
    }
}
