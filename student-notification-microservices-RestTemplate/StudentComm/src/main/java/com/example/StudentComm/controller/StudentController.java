package com.example.StudentComm.controller;

import com.example.StudentComm.entity.Student;
import com.example.StudentComm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student)
    {
        Student stu = studentService.saveStudent(student);
       return new ResponseEntity<Student>(stu, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id)
    {
        String message =studentService.deleteStudent(id);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping("/getAllStudent")
    public List<Student> getNotifications() {
        return studentService.getAllStudent();
    }

}
