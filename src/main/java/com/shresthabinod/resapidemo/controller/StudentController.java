package com.shresthabinod.resapidemo.controller;

import com.shresthabinod.resapidemo.model.Student;
import com.shresthabinod.resapidemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
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
    @PostMapping("/students/create")
    public Student postCustomer(@RequestBody Student student) {

        Student _student = studentRepository.save(new Student(student.getName(), student.getAge(), student.getGrade()));
        return _student;
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {
        System.out.println("Delete Student with ID = " + id + "...");

        studentRepository.deleteById(id);

        return new ResponseEntity<>("Student has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/students/delete")
    public ResponseEntity<String> deleteAllCustomers() {
        System.out.println("Delete All Customers...");

        studentRepository.deleteAll();

        return new ResponseEntity<>("All students have been deleted!", HttpStatus.OK);
    }

    @GetMapping("students/age/{age}")
    public List<Student> findByAge(@PathVariable int age) {

        List<Student> students = studentRepository.findByAge(age);
        return students;
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateCustomer(@PathVariable("id") String id, @RequestBody Student student) {
        System.out.println("Update Student with ID = " + id + "...");

        Optional<Student> customerData = studentRepository.findById(id);

        if (customerData.isPresent()) {
            Student _student = customerData.get();
            _student.setName(student.getName());
            _student.setAge(student.getAge());
            _student.setGrade(student.getGrade());
            _student.setActive(student.isActive());
            return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}

