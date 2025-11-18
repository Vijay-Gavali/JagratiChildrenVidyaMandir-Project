package com.jagratichildrenvidyamandir.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.service.ClassService;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping
    public ResponseEntity<ClassDTO> create(@RequestBody ClassDTO dto) {
        return new ResponseEntity<>(classService.createClass(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> get(@PathVariable Integer id) {
        ClassDTO data = classService.getClassById(id);
        return data == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(data);
    }

    @GetMapping
    public List<ClassDTO> getAll() {
        return classService.getAllClasses();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassDTO> update(@PathVariable Integer id, @RequestBody ClassDTO dto) {
        ClassDTO updated = classService.updateClass(id, dto);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = classService.deleteClass(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
