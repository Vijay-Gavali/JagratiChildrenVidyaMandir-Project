package com.jagratichildrenvidyamandir.controller;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.ClassWithTeachersDTO;
import com.jagratichildrenvidyamandir.service.ClassService;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping("/{sessionId}/save")
    public ResponseEntity<?> createClassForSession(
            @PathVariable Integer sessionId,
            @RequestBody ClassDTO dto) {

        try {
            dto.setSessionId(sessionId); // 🔥 enforce session from URL
            ClassDTO saved = classService.createClass(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/{sessionId}/getAllUsingSessionId")
    public ResponseEntity<?> getClassesBySession(
            @PathVariable Integer sessionId) {

        try {
            List<ClassDTO> classes =
                    classService.getClassesBySessionId(sessionId);
            return ResponseEntity.ok(classes);

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(classService.getClassById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/{sessionId}/getAll")
    public List<ClassDTO> getAll(@PathVariable Integer sessionId) {
        return classService.getClassesBySessionId(sessionId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody ClassDTO dto) {

        try {
            ClassDTO updated = classService.updateClass(id, dto);
            return ResponseEntity.ok(updated);

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            classService.deleteClass(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        }
    }


    @GetMapping("/{classId}/teachers-full")
    public ClassWithTeachersDTO getClassTeachersFull(
            @PathVariable Integer classId) {

        return classService.getClassWithTeachers(classId);
    }
}
