package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.FeesDTO;
import com.jagratichildrenvidyamandir.service.FeesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeesController {

    private final FeesService service;

    public FeesController(FeesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FeesDTO> create(@RequestBody FeesDTO dto) {
        return new ResponseEntity<>(service.createFees(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeesDTO> get(@PathVariable Integer id) {
        FeesDTO dto = service.getFeesById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<FeesDTO> getAll() {
        return service.getAllFees();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeesDTO> update(@PathVariable Integer id, @RequestBody FeesDTO dto) {
        FeesDTO updated = service.updateFees(id, dto);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return service.deleteFees(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
