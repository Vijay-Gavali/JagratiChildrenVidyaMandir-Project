package com.jagratichildrenvidyamandir.service;

import com.jagratichildrenvidyamandir.dto.TeacherDocumentDTO;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.entity.TeacherDocument;
import com.jagratichildrenvidyamandir.enums.DocumentType;
import com.jagratichildrenvidyamandir.repository.TeacherDocumentRepository;
import com.jagratichildrenvidyamandir.repository.TeacherRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherDocumentService {

    private final TeacherDocumentRepository docRepo;
    private final TeacherRepository teacherRepo;
    private final String BASE_DIR = "uploads/teachers/";

    public TeacherDocumentService(TeacherDocumentRepository docRepo, TeacherRepository teacherRepo) {
        this.docRepo = docRepo;
        this.teacherRepo = teacherRepo;
    }

    // ---------- UPLOAD ----------
    public TeacherDocumentDTO upload(Integer teacherId, DocumentType type, MultipartFile file) throws Exception {
        validateFileAndTeacher(teacherId, file);
        validateType(type);

        Teacher teacher = teacherRepo.findById(teacherId).get();
        String folder = BASE_DIR + teacherId + "/" + type.name() + "/";
        createFolder(folder);

        String uniqueName = type.name() + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Path.of(folder + uniqueName);
        Files.write(filePath, file.getBytes());

        TeacherDocument doc = new TeacherDocument();
        doc.setTeacher(teacher);
        doc.setType(type);
        doc.setFilePath(filePath.toString());
        doc.setOriginalName(file.getOriginalFilename());
        doc.setUploadedAt(LocalDateTime.now());

        return toDTO(docRepo.save(doc));
    }

    // ---------- UPDATE / REPLACE ----------
    public TeacherDocumentDTO replace(Integer teacherId, DocumentType type, MultipartFile file) throws Exception {
        validateFileAndTeacher(teacherId, file);
        validateType(type);

        Teacher teacher = teacherRepo.findById(teacherId).get();
        List<TeacherDocument> existing = docRepo.findByTeacherTeacherIdAndType(teacherId, type);
        TeacherDocument target;

        if (!existing.isEmpty()) {
            target = existing.get(0);
            // delete old file
            try { Files.deleteIfExists(Path.of(target.getFilePath())); } catch (Exception ignored) {}
            // remove duplicates
            for (int i = 1; i < existing.size(); i++) {
                TeacherDocument dup = existing.get(i);
                try { Files.deleteIfExists(Path.of(dup.getFilePath())); } catch (Exception ignored) {}
                docRepo.delete(dup);
            }
        } else {
            target = new TeacherDocument();
            target.setTeacher(teacher);
            target.setType(type);
        }

        String folder = BASE_DIR + teacherId + "/" + type.name() + "/";
        createFolder(folder);

        String uniqueName = type.name() + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path newPath = Path.of(folder + uniqueName);
        Files.write(newPath, file.getBytes());

        target.setFilePath(newPath.toString());
        target.setOriginalName(file.getOriginalFilename());
        target.setUploadedAt(LocalDateTime.now());

        return toDTO(docRepo.save(target));
    }

    // ---------- DELETE ----------
    public boolean delete(Integer teacherId, DocumentType type) {
        validateType(type);
        List<TeacherDocument> docs = docRepo.findByTeacherTeacherIdAndType(teacherId, type);
        if (docs.isEmpty()) return false;

        for (TeacherDocument doc : docs) {
            try { Files.deleteIfExists(Path.of(doc.getFilePath())); } catch (Exception ignored) {}
            docRepo.delete(doc);
        }
        return true;
    }

    // ---------- LIST ----------
    public List<TeacherDocumentDTO> listByTeacher(Integer teacherId) {
        return docRepo.findByTeacherTeacherId(teacherId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<TeacherDocumentDTO> listAllDocuments() {
        return docRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // ---------- GET FILE ----------
    public File getFile(Integer teacherId, DocumentType type) {
        return docRepo.findByTeacherTeacherIdAndType(teacherId, type)
                .stream().findFirst()
                .map(TeacherDocument::getFilePath)
                .map(File::new)
                .orElse(null);
    }

    // ---------- STREAM FILE ----------
    public static class FileResult {
        public final Resource resource;
        public final String filename;
        public final String mimeType;
        public FileResult(Resource resource, String filename, String mimeType) {
            this.resource = resource;
            this.filename = filename;
            this.mimeType = mimeType;
        }
    }

    public FileResult getDocumentResource(Integer teacherId, DocumentType type) throws Exception {
        List<TeacherDocument> docs = docRepo.findByTeacherTeacherIdAndType(teacherId, type);
        if (docs.isEmpty()) return null;

        TeacherDocument doc = docs.get(0);
        Path path = Paths.get(doc.getFilePath()).toAbsolutePath().normalize();
        if (!Files.exists(path) || !Files.isReadable(path)) return null;

        Resource resource = new UrlResource(path.toUri());
        String mime = Files.probeContentType(path);
        if (mime == null) mime = "application/octet-stream";

        return new FileResult(resource, doc.getOriginalName(), mime);
    }

    // ---------- HELPERS ----------
    private TeacherDocumentDTO toDTO(TeacherDocument doc) {
        TeacherDocumentDTO dto = new TeacherDocumentDTO();
        dto.setDocumentId(doc.getDocumentId());
        dto.setTeacherId(doc.getTeacher().getTeacherId());
        dto.setType(doc.getType());
        dto.setFilePath(doc.getFilePath());
        dto.setOriginalName(doc.getOriginalName());
        dto.setUploadedAt(doc.getUploadedAt());
        return dto;
    }

    private void createFolder(String folderPath) {
        File dir = new File(folderPath);
        if (!dir.exists()) dir.mkdirs();
    }

    private void validateFileAndTeacher(Integer teacherId, MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) throw new Exception("File is empty");
        if (teacherRepo.findById(teacherId).isEmpty()) throw new Exception("Teacher not found");
    }

    private void validateType(DocumentType type) {
        if (type != DocumentType.TEACHER_PHOTO &&
            type != DocumentType.TEACHER_AADHAR &&
            type != DocumentType.TEACHER_PAN &&
            type != DocumentType.TEACHER_DEGREE &&
            type != DocumentType.TEACHER_CERTIFICATE) {
            throw new IllegalArgumentException("Invalid document type");
        }
    }
}
