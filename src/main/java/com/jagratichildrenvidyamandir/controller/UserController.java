package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        UserDTO created = service.createUser(dto);
        if (created == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Integer id) {
        UserDTO data = service.getUserById(id);
        return data == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(data);
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAll() {
        return service.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO dto) {
        UserDTO updated = service.updateUser(id, dto);
        if (updated == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = service.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Additional endpoints to fetch by unique fields
    @GetMapping("/byAdmissionNo/{admissionNo}")
    public ResponseEntity<UserDTO> getByAdmissionNo(@PathVariable String admissionNo) {
        UserDTO dto = service.getByAdmissionNo(admissionNo);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        UserDTO dto = service.getByEmail(email);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @GetMapping("/byPhone/{phone}")
    public ResponseEntity<UserDTO> getByPhone(@PathVariable String phone) {
        UserDTO dto = service.getByPhone(phone);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @GetMapping("/byAadhar/{aadhar}")
    public ResponseEntity<UserDTO> getByAadhar(@PathVariable String aadhar) {
        UserDTO dto = service.getByAadhar(aadhar);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }
}
