package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.NoticeDTO;
import com.jagratichildrenvidyamandir.service.NoticeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService service;

    public NoticeController(NoticeService service) {
        this.service = service;
    }

    // 1. Get all notices for a specific session - RESTful style
    @GetMapping("/{sessionId}/getAllUsingSessionId")
    public ResponseEntity<?> getNoticesBySession(@PathVariable Integer sessionId) {
        try {
            List<NoticeDTO> notices = service.getNoticesBySessionId(sessionId);
            return ResponseEntity.ok(notices);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    // 2. Create notice for a specific session - RESTful style
    @PostMapping("/{sessionId}/save")
    public ResponseEntity<?> createNoticeForSession(@PathVariable Integer sessionId, @RequestBody NoticeDTO dto) {
        try {
            dto.setSessionId(sessionId); // Set sessionId from URL
            NoticeDTO saved = service.createNotice(dto);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    // 3. Get all notices (without session filter)
    @GetMapping("/GetAll")
    public ResponseEntity<?> getAllNotices() {
        try {
            List<NoticeDTO> notices = service.getAllNotices();
            return ResponseEntity.ok(notices);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    // 4. Get notice by ID (standalone)
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoticeById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getNoticeById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        }
    }

    // 5. Update notice by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable Integer id, @RequestBody NoticeDTO dto) {
        try {
            return ResponseEntity.ok(service.updateNotice(id, dto));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        }
    }

    // 6. Delete notice by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable Integer id) {
        try {
            service.deleteNotice(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
        }
    }
}