package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.AdminDTO;
import com.jagratichildrenvidyamandir.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminDTO req) {
        try {
            AdminDTO dto = adminService.login(req);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(
                    java.util.Map.of("error", ex.getMessage())
            );
        }
    }
}
