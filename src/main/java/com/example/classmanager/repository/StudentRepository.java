package com.example.classmanager.repository;

import com.example.classmanager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Additional query methods can be defined here if needed
}
