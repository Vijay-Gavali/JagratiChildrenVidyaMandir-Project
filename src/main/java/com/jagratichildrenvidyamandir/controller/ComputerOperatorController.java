package com.jagratichildrenvidyamandir.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jagratichildrenvidyamandir.dto.ComputerOperatorDTO;
import com.jagratichildrenvidyamandir.service.ComputerOperatorService;

@RestController
@RequestMapping("/api/computer-operators")
public class ComputerOperatorController {

	private final ComputerOperatorService service;

	public ComputerOperatorController(ComputerOperatorService service) {
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ComputerOperatorDTO dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<ComputerOperatorDTO>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		ComputerOperatorDTO dto = service.getById(id);
		return dto != null ? ResponseEntity.ok(dto)
				: ResponseEntity.status(404).body(Map.of("error", "Computer Operator not found"));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ComputerOperatorDTO dto) {

		ComputerOperatorDTO updated = service.update(id, dto);
		return updated != null ? ResponseEntity.ok(updated)
				: ResponseEntity.status(404).body(Map.of("error", "Computer Operator not found"));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody ComputerOperatorDTO dto) {
		ComputerOperatorDTO result = service.login(dto.getPhoneNumber(), dto.getPassword());

		return result != null ? ResponseEntity.ok(result)
				: ResponseEntity.status(401).body(Map.of("error", "Invalid phone number or password"));
	}
}
