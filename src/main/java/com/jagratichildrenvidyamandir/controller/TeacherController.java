package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.*;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.repository.ClassRepository;
import com.jagratichildrenvidyamandir.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final AttendanceService attendanceService;
    private final UserService userService;
    private final UserExcelService excelService;
    private final ClassRepository classRepository;

    public TeacherController(
            TeacherService teacherService,
            AttendanceService attendanceService,
            UserService userService,
            UserExcelService excelService,
            ClassRepository classRepository) {

        this.teacherService = teacherService;
        this.attendanceService = attendanceService;
        this.userService = userService;
        this.excelService = excelService;
        this.classRepository = classRepository;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<TeacherDTO> registerTeacher(@RequestBody TeacherDTO dto) {
        return ResponseEntity.ok(teacherService.registerTeacher(dto));
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(
            @PathVariable Integer id,
            @RequestBody TeacherDTO dto) {

        return ResponseEntity.ok(teacherService.updateTeacher(id, dto));
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<Teacher> login(@RequestBody LoginRequest req) {
        Teacher teacher =
                teacherService.authenticateTeacher(req.getPhone(), req.getPassword());

        if (teacher == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(teacher);
    }

    // ================= CLASSES BY TEACHER =================
    @GetMapping("/{teacherId}/classes")
    public ResponseEntity<List<ClassDTO>> getClassesByTeacher(
            @PathVariable Integer teacherId) {

        return ResponseEntity.ok(
                teacherService.getClassesByTeacher(teacherId)
        );
    }

    // ================= STUDENTS BY TEACHER =================
    @GetMapping("/{teacherId}/students")
    public ResponseEntity<List<UserDTO>> getStudentsByTeacher(
            @PathVariable Integer teacherId) {

        return ResponseEntity.ok(
                teacherService.getStudentsByTeacher(teacherId)
        );
    }

    // ================= STUDENTS BY TEACHER & CLASS =================
    @GetMapping("/{teacherId}/class/{classId}/students")
    public ResponseEntity<List<Map<String, Object>>> getStudentsByTeacherClass(
            @PathVariable Integer teacherId,
            @PathVariable Integer classId) {

        List<Map<String, Object>> students =
                teacherService.getStudentsByTeacherAndClass(teacherId, classId);

        if (students.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(students);
    }

    // ================= LOGIN REQUEST =================
    public static class LoginRequest {
        private String phone;
        private String password;

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
