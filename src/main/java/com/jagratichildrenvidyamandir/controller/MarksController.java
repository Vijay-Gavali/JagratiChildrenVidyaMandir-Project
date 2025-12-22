package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.MarksDTO;
import com.jagratichildrenvidyamandir.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/marks")
public class MarksController {

    @Autowired
    private MarksService service;

    // ✅ ADD SINGLE STUDENT MARKS
    @PostMapping
    public ResponseEntity<?> add(@RequestBody MarksDTO dto) {

        MarksDTO saved = service.addMarks(dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Marks added successfully");
        response.put("data", saved);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ✅ ADD MULTIPLE STUDENTS MARKS AT ONCE
    @PostMapping("/bulk")
    public ResponseEntity<?> addBulk(@RequestBody List<MarksDTO> dtoList) {

        List<MarksDTO> savedList = service.addBulkMarks(dtoList);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bulk marks added successfully");
        response.put("totalRecords", savedList.size());
        response.put("data", savedList);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MarksDTO dto) {

        MarksDTO updated = service.updateMarks(id, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Marks updated successfully");
        response.put("data", updated);

        return ResponseEntity.ok(response);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        service.deleteMarks(id);

        return ResponseEntity.ok(Map.of("message", "Marks deleted successfully"));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<MarksDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ✅ GET BY STUDENT
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<MarksDTO>> getByStudent(@PathVariable Integer studentId) {
        return ResponseEntity.ok(service.getByStudent(studentId));
    }
}	