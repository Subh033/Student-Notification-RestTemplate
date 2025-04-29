package com.example.StudentComm.service;

import com.example.StudentComm.entity.Student;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    Student updateStudent(String id, Student student);

    String deleteStudent(String id);
    List<Student> getAllStudent();
}
