package com.artustech.crud;

import com.artustech.crud.dao.StudentDAO;
import com.artustech.crud.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOmpl implements StudentDAO {
    private EntityManager entityManager;

    @Autowired
    public StudentDAOmpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return this.entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> typedQuery = this.entityManager.createQuery("FROM Student order by lastName asc",Student.class);
        return typedQuery.getResultList() ;
    }

    @Override
    public List<Student> findByLastName(String string) {
        TypedQuery<Student> typedQuery = this.entityManager.createQuery("FROM Student where lastName = :string",Student.class);
        typedQuery.setParameter("string",string);
        return typedQuery.getResultList() ;
    }

    @Override
    @Transactional
    public void update(Student student) {
        this.entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Student student = this.entityManager.find(Student.class,id);
        this.entityManager.remove(student);
    }
}
