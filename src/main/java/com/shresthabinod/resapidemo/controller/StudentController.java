package com.shresthabinod.resapidemo.controller;

import com.shresthabinod.resapidemo.model.Student;
import com.shresthabinod.resapidemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        System.out.println("Get all the students....");
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }
    
}

