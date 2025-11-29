package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Implements save method
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
}
