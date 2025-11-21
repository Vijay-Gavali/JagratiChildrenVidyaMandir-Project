package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.ParentDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
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
        ParentDTO dto = service.getParentById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
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

    // Parent login: phone + password -> returns children list
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        ParentDTO p = service.authenticateParent(req.getPhone(), req.getPassword());
        if (p == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        List<UserDTO> children = service.getChildrenByParentPhone(p.getPhone());
        return ResponseEntity.ok(children);
    }

    public static class LoginRequest {
        private String phone;
        private String password;
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
