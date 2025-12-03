package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.DocumentDTO;
import com.jagratichildrenvidyamandir.enums.DocumentType;
import com.jagratichildrenvidyamandir.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping("/upload/{userId}/{type}")
    public ResponseEntity<?> upload(
            @PathVariable Integer userId,
            @PathVariable DocumentType type,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            DocumentDTO dto = service.uploadDocument(userId, type, file);
            if (dto == null) return ResponseEntity.badRequest().body("Invalid user or file");
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public List<DocumentDTO> getDocuments(@PathVariable Integer userId) {
        return service.getUserDocuments(userId);
    }

    /**
     * New endpoint: create or replace document for user by type.
     * URL: PUT /api/documents/update/{userId}/{type}
     * Body: form-data with key "file" (File)
     */
    @PutMapping("/update/{userId}/{type}")
    public ResponseEntity<?> updateDocumentByUserAndType(
            @PathVariable Integer userId,
            @PathVariable DocumentType type,
            @RequestParam("file") MultipartFile file) {
        try {
            DocumentDTO dto = service.updateOrReplaceByUserAndType(userId, type, file);
            if (dto == null) return ResponseEntity.badRequest().body("Invalid user or file");
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
