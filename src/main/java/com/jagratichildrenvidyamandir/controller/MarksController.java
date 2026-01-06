package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.MarksDTO;
import com.jagratichildrenvidyamandir.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/marks")
public class MarksController {

    @Autowired
    private MarksService service;

    // ✅ ADD SINGLE
    /*@PostMapping
    public ResponseEntity<?> add(@RequestBody MarksDTO dto) {

        MarksDTO saved = service.addMarks(dto);

        return new ResponseEntity<>(Map.of(
                "message", "Marks added successfully",
                "data", saved
        ), HttpStatus.CREATED);
    }*/

    // ✅ ADD BULK
    @PostMapping("/bulk")
    public ResponseEntity<?> addBulk(@RequestBody List<MarksDTO> dtoList) {

        List<MarksDTO> saved = service.addBulkMarks(dtoList);

        return new ResponseEntity<>(Map.of(
                "message", "Bulk marks added successfully",
                "totalRecords", saved.size(),
                "data", saved
        ), HttpStatus.CREATED);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody MarksDTO dto) {

        return ResponseEntity.ok(Map.of(
                "message", "Marks updated successfully",
                "data", service.updateMarks(id, dto)
        ));
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
    public ResponseEntity<List<MarksDTO>> getByStudent(
            @PathVariable Integer studentId) {

        return ResponseEntity.ok(service.getByStudent(studentId));
    }
}