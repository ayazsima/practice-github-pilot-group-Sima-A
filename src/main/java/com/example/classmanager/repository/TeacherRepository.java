package com.example.classmanager.repository;

import com.example.classmanager.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Additional query methods can be defined here if needed
}
