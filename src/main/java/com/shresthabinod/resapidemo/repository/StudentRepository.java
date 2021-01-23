package com.shresthabinod.resapidemo.repository;

import com.shresthabinod.resapidemo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends MongoRepository<Student, Long> {
    List<Student> findByAge(int Age);


}
