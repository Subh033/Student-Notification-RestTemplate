package com.example.StudentComm.service;

import com.example.StudentComm.entity.Student;
import com.example.StudentComm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;
//    @Override
//    public Student saveStudent(Student student) {
//        String message = "Welcome " + student.getName() + " and Your id is " + student.getId() ;
//        restTemplate.postForObject("http://localhost:8082/notifications/rM", message, String.class);
//        return studentRepository.save(student);
//    }
    @Override
    public Student saveStudent(Student student) {
        Map<String, String> request = new HashMap<>();
        request.put("id", student.getId());
        request.put("message", "Welcome " + student.getName() + " to the Java course!");
        restTemplate.postForObject("http://localhost:8082/notifications/recieveMessage", request, String.class);
        return studentRepository.save(student);
    }


    @Override
    public Student updateStudent(String id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(student.getName());

            // Sending update notification via PUT
            String message = "Student " + existingStudent.getName() + " updated info";
            restTemplate.put("http://localhost:8082/notifications/update/" + id, message);

            return studentRepository.save(existingStudent);
        } else {
            throw new RuntimeException("Student not found with ID: " + id);
        }
    }

    @Override
    public String deleteStudent(String id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            studentRepository.deleteById(id);

            String message = "Student " + student.getName() + " (ID: " + id + ") has been removed.";
            restTemplate.delete("http://localhost:8082/notifications/delete/" + id); // You could also use POST if delete doesn't accept body

            return "Student with ID " + id + " deleted successfully";
        } else {
            throw new RuntimeException("Student not found with ID: " + id);
        }
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

}
