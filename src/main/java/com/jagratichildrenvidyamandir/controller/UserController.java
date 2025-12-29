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

    // GET user by ID within a session
    @GetMapping("/{sessionId}/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Integer sessionId, @PathVariable Integer id) {
        UserDTO dto = service.getUserById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    // CREATE user with session ID
    @PostMapping("/{sessionId}/save")
    public ResponseEntity<UserDTO> create(@PathVariable Integer sessionId, @RequestBody UserDTO dto) {
        UserDTO created = service.createUser(sessionId, dto);
        if (created == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET all users for a specific session
    @GetMapping("/{sessionId}/getAll")
    public List<UserDTO> getAll(@PathVariable Integer sessionId) {
        return service.getAllUsers(sessionId);
    }

    // UPDATE user with session context
    @PutMapping("/{sessionId}/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer sessionId, @PathVariable Integer id, @RequestBody UserDTO dto) {
        UserDTO updated = service.updateUser(id, dto, sessionId);
        return updated == null ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.ok(updated);
    }

    // DELETE user within a session context
    @DeleteMapping("/{sessionId}/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer sessionId, @PathVariable Integer id) {
        boolean deleted = service.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // LOGIN (No session ID in path as it's the entry point)
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequest req) {
        UserDTO user = service.authenticateStudent(req.getAdmissionNo(), req.getPassword());
        return user == null ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() : ResponseEntity.ok(user);
    }

    // UPLOAD EXCEL for a specific session
    @PostMapping("/{sessionId}/uploadExcel")
    public ResponseEntity<UploadSummaryDTO> uploadExcel(@PathVariable Integer sessionId, @RequestParam("file") MultipartFile file) {
        // Pass sessionId here as well
        UploadSummaryDTO result = excelService.importFromExcel(file, sessionId);
        return ResponseEntity.ok(result);
    }

    // Helper class for Login
    public static class LoginRequest {
        private String admissionNo;
        private String password;

        public String getAdmissionNo() {
            return admissionNo;
        }

        public void setAdmissionNo(String admissionNo) {
            this.admissionNo = admissionNo;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}