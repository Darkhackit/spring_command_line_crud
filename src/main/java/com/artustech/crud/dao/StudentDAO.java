package com.artustech.crud.dao;

import com.artustech.crud.entity.Student;

import java.util.List;

public interface StudentDAO {

    public void  save(Student student);

    public Student findById(int id);

    List<Student> findAll();
    List<Student> findByLastName(String string);

    void update(Student student);

    void delete(int id);

}
