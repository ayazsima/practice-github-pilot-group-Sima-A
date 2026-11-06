package com.example.classmanager.controller;

import com.example.classmanager.model.Teacher;
import com.example.classmanager.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(
            @PathVariable("id") Long id) {

        return teacherRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(
            @RequestBody Teacher teacher) {

        teacher.setId(null);

        Teacher savedTeacher = teacherRepository.save(teacher);

        URI location = URI.create("/teachers/" + savedTeacher.getId());

        return ResponseEntity.created(location).body(savedTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable("id") Long id,
            @RequestBody Teacher teacherDetails) {

        return teacherRepository.findById(id)
                .map(teacher -> {
                    teacher.setFirstName(teacherDetails.getFirstName());
                    teacher.setLastName(teacherDetails.getLastName());
                    teacher.setEmail(teacherDetails.getEmail());

                    Teacher updatedTeacher = teacherRepository.save(teacher);

                    return ResponseEntity.ok(updatedTeacher);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(
            @PathVariable("id") Long id) {

        return teacherRepository.findById(id)
                .map(teacher -> {
                    teacherRepository.delete(teacher);

                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}