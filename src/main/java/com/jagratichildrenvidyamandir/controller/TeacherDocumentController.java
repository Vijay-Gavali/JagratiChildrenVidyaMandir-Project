package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.TeacherDocumentDTO;
import com.jagratichildrenvidyamandir.enums.DocumentType;
import com.jagratichildrenvidyamandir.service.TeacherDocumentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/teacher-documents")
public class TeacherDocumentController {

    private final TeacherDocumentService service;

    public TeacherDocumentController(TeacherDocumentService service) {
        this.service = service;
    }

    @PostMapping("/upload/{teacherId}/{type}")
    public ResponseEntity<?> upload(@PathVariable Integer teacherId,
                                    @PathVariable String type,
                                    @RequestParam("file") MultipartFile file) {
        try {
            DocumentType docType = parseType(type);
            TeacherDocumentDTO dto = service.upload(teacherId, docType, file);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{teacherId}/{type}")
    public ResponseEntity<?> update(@PathVariable Integer teacherId,
                                    @PathVariable String type,
                                    @RequestParam("file") MultipartFile file) {
        try {
            DocumentType docType = parseType(type);
            TeacherDocumentDTO dto = service.replace(teacherId, docType, file);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{teacherId}/{type}")
    public ResponseEntity<?> delete(@PathVariable Integer teacherId, @PathVariable String type) {
        DocumentType docType = parseType(type);
        boolean deleted = service.delete(teacherId, docType);
        if (deleted) return ResponseEntity.ok("Document deleted successfully");
        return ResponseEntity.status(404).body("Document not found");
    }

    @GetMapping("/{teacherId}")
    public List<TeacherDocumentDTO> listByTeacher(@PathVariable Integer teacherId) {
        return service.listByTeacher(teacherId);
    }

    @GetMapping("/all")
    public List<TeacherDocumentDTO> listAll() {
        return service.listAllDocuments();
    }

    @GetMapping("/download/{teacherId}/{type}")
    public ResponseEntity<?> download(@PathVariable Integer teacherId, @PathVariable String type) {
        try {
            DocumentType docType = parseType(type);
            TeacherDocumentService.FileResult fr = service.getDocumentResource(teacherId, docType);
            if (fr == null) return ResponseEntity.status(404).body("File not found");

            Resource resource = fr.resource;
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fr.mimeType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fr.filename + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Helper: convert string to enum
    private DocumentType parseType(String type) {
        try {
            return DocumentType.valueOf(type.trim().toUpperCase(Locale.ROOT).replace("-", "_"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid document type: " + type);
        }
    }
}
