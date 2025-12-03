package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.UploadSummaryDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.service.UserExcelService;
import com.jagratichildrenvidyamandir.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;
	private final UserExcelService excelService;

	public UserController(UserService service, UserExcelService excelService) {
		this.service = service;
		this.excelService = excelService;
	}

	@PostMapping("/save")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
		UserDTO created = service.createUser(dto);
		if (created == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public List<UserDTO> getAll() {
		return service.getAllUsers();
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO dto) {
		UserDTO updated = service.updateUser(id, dto);
		return updated == null ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = service.deleteUser(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	// Student login endpoint: POST /api/users/login
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody LoginRequest req) {
		UserDTO user = service.authenticateStudent(req.getPhone(), req.getPassword());
		return user == null ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() : ResponseEntity.ok(user);
	}

	// Excel upload endpoint
	// Use Postman: Body -> form-data -> key "file" (type File)
	@PostMapping("/uploadExcel")
	public ResponseEntity<UploadSummaryDTO> uploadExcel(@RequestParam("file") MultipartFile file) {
		if (file == null || file.isEmpty()) {
			UploadSummaryDTO empty = new UploadSummaryDTO();
			empty.addError("No file uploaded");
			return ResponseEntity.badRequest().body(empty);
		}
		UploadSummaryDTO result = excelService.importFromExcel(file);
		return ResponseEntity.ok(result);
	}

	public static class LoginRequest {
		private String phone;
		private String password;

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
