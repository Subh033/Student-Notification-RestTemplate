package com.example.StudentComm.repository;

import com.example.StudentComm.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
}
