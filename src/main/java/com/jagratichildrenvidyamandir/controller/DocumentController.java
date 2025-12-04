package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.DocumentDTO;
import com.jagratichildrenvidyamandir.enums.DocumentType;
import com.jagratichildrenvidyamandir.service.DocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;
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

    @DeleteMapping("/delete/{userId}/{type}")
    public ResponseEntity<?> deleteDocument(
            @PathVariable Integer userId,
            @PathVariable String type
    ) {
        // normalize input: trim, uppercase, replace hyphen with underscore
        if (type == null) {
            return ResponseEntity.badRequest().body("type is required");
        }
        String normalized = type.trim().toUpperCase(Locale.ROOT).replace("-", "_");

        // Accept cases like "student_aadhar", "STUDENT-AADHAR", "studentAadhar" (we handle the common ones)
        // Try a few normalization strategies:
        DocumentType docType = null;
        try {
            // direct lookup first
            docType = DocumentType.valueOf(normalized);
        } catch (IllegalArgumentException e1) {
            // try convert camelCase to UPPER_SNAKE (e.g. studentAadhar -> STUDENT_AADHAR)
            String snake = normalized
                    .replaceAll("([a-z])([A-Z])", "$1_$2") // camel->snake if any
                    .replaceAll("[\\s]+", "_");
            snake = snake.toUpperCase(Locale.ROOT);
            try {
                docType = DocumentType.valueOf(snake);
            } catch (IllegalArgumentException e2) {
                // Not a known type — return clear 400
                return ResponseEntity.badRequest().body("Unknown document type: '" + type + "'. Allowed: " + String.join(", ",
                        java.util.Arrays.stream(DocumentType.values()).map(Enum::name).toArray(String[]::new)
                ));
            }
        }

        boolean deleted = service.deleteDocument(userId, docType);

        if (!deleted) {
            return ResponseEntity.status(404)
                    .body("No document found for userId=" + userId + " and type=" + docType);
        }

        return ResponseEntity.ok("Document deleted successfully");
    }


    /**
     * Download endpoint:
     * GET /api/documents/download/{userId}/{type}
     * Streams the file as attachment with the original filename.
     */
    @GetMapping("/download/{userId}/{type}")
    public ResponseEntity<?> downloadDocument(
            @PathVariable Integer userId,
            @PathVariable String type
    ) {
        // normalize and parse type (same logic as delete)
        if (type == null) {
            return ResponseEntity.badRequest().body("type is required");
        }
        String normalized = type.trim().toUpperCase(Locale.ROOT).replace("-", "_");

        DocumentType docType = null;
        try {
            docType = DocumentType.valueOf(normalized);
        } catch (IllegalArgumentException e1) {
            String snake = normalized
                    .replaceAll("([a-z])([A-Z])", "$1_$2")
                    .replaceAll("[\\s]+", "_");
            snake = snake.toUpperCase(Locale.ROOT);
            try {
                docType = DocumentType.valueOf(snake);
            } catch (IllegalArgumentException e2) {
                return ResponseEntity.badRequest().body("Unknown document type: '" + type + "'. Allowed: " + String.join(", ",
                        java.util.Arrays.stream(DocumentType.values()).map(Enum::name).toArray(String[]::new)
                ));
            }
        }

        try {
            DocumentService.FileResult fr = service.getDocumentResource(userId, docType);
            if (fr == null) {
                return ResponseEntity.status(404)
                        .body("Document not found for userId=" + userId + " and type=" + docType);
            }

            Resource resource = fr.resource;
            String filename = fr.filename != null ? fr.filename : "download";
            String mime = fr.mimeType != null ? fr.mimeType : "application/octet-stream";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mime))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
