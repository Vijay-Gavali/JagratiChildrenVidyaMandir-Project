//package com.jagratichildrenvidyamandir.controller;
//
//import com.jagratichildrenvidyamandir.dto.TeacherDTO;
//import com.jagratichildrenvidyamandir.service.TeacherDocumentExtractService;
//import com.jagratichildrenvidyamandir.service.TeacherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@RestController
//@RequestMapping("/api/teacher")
//public class TeacherDocumentUploadController {
//
//    @Autowired
//    private TeacherService teacherService;
//
//    @Autowired
//    private TeacherDocumentExtractService extractService;
//
//    @PostMapping("/register")
//    public ResponseEntity<TeacherDTO> registerTeacherWithDocument(
//            @RequestPart("teacherData") TeacherDTO dto,
//            @RequestPart("file") MultipartFile file) throws Exception {
//
//        // 1️⃣ Save uploaded file locally
//        String uploadDir = "Uploads/Docs/";
//        Path dirPath = Paths.get(uploadDir);
//        if (!Files.exists(dirPath)) {
//            Files.createDirectories(dirPath);
//        }
//        String filePath = uploadDir + System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        Files.write(Paths.get(filePath), file.getBytes());
//
//        // 2️⃣ Extract info from Aadhaar/photo using OCR
//        TeacherDocumentExtractService.ExtractedData extractedData = extractService.processDocument(filePath);
//
//        // 3️⃣ Fill extracted info into DTO
//        dto.setAadharNo(extractedData.getAadharNo());
//        dto.setAddress(extractedData.getAddress());
//        dto.setDocumentPath(filePath);
//        dto.setDateOfBirth(extractedData.getDob()); // Directly use LocalDate
//
//        // 4️⃣ Save teacher record
//        TeacherDTO savedTeacher = teacherService.registerTeacher(dto);
//
//        return ResponseEntity.ok(savedTeacher);
//    }
//}
