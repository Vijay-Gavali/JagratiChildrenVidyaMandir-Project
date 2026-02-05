package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.SessionDTO;
import com.jagratichildrenvidyamandir.service.SessionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

	private final SessionService service;

	public SessionController(SessionService service) {
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<?> create(@RequestBody SessionDTO dto) {
		try {
			SessionDTO saved = service.create(dto);
			return ResponseEntity.status(201).body(saved);
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
		}
	}

	@GetMapping("/getAll")
	public List<SessionDTO> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(service.getById(id));
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SessionDTO dto) {
		try {
			return ResponseEntity.ok(service.update(id, dto));
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
		}
	}
	
	

}
