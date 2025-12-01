package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.EnquiryDTO;
import com.jagratichildrenvidyamandir.service.EnquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryService service;

    public EnquiryController(EnquiryService service) {
        this.service = service;
    }

    // Create
    @PostMapping("/save")
    public ResponseEntity<EnquiryDTO> createEnquiry(@Valid @RequestBody EnquiryDTO dto) {
        EnquiryDTO created = service.createEnquiry(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get all
    @GetMapping("/getAll")
    public ResponseEntity<List<EnquiryDTO>> getAll() {
        return ResponseEntity.ok(service.getAllEnquiries());
    }

    // Get one
    @GetMapping("/{id}")
    public ResponseEntity<EnquiryDTO> getById(@PathVariable Integer id) {
        EnquiryDTO dto = service.getEnquiryById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<EnquiryDTO> update(@PathVariable Integer id, @Valid @RequestBody EnquiryDTO dto) {
        EnquiryDTO updated = service.updateEnquiry(id, dto);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = service.deleteEnquiry(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
