package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.ParentDTO;
import com.jagratichildrenvidyamandir.service.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentController {

    private final ParentService service;

    public ParentController(ParentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ParentDTO> create(@RequestBody ParentDTO dto) {
        ParentDTO created = service.createParent(dto);
        if (created == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDTO> get(@PathVariable Integer id) {
        ParentDTO data = service.getParentById(id);
        return data == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(data);
    }

    @GetMapping("/getAll")
    public List<ParentDTO> getAll() {
        return service.getAllParents();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentDTO> update(@PathVariable Integer id, @RequestBody ParentDTO dto) {
        ParentDTO updated = service.updateParent(id, dto);
        if (updated == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = service.deleteParent(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
