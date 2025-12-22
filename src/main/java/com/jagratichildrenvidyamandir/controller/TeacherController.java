package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.controller.UserController.LoginRequest;
import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.MarksDTO;
import com.jagratichildrenvidyamandir.dto.TeacherDTO;

import com.jagratichildrenvidyamandir.dto.UploadSummaryDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.repository.ClassRepository;
import com.jagratichildrenvidyamandir.service.AttendanceService;
import com.jagratichildrenvidyamandir.service.TeacherService;
import com.jagratichildrenvidyamandir.service.UserExcelService;
import com.jagratichildrenvidyamandir.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final AttendanceService attendanceService;
    private final UserService service;
    private final UserExcelService excelService;
    private final String BASE_DIR = "uploads/teachers/";
    private final ClassRepository classRepository;


    @Autowired
    public TeacherController(TeacherService teacherService, AttendanceService attendanceService,
                              UserService service,
                             UserExcelService excelService, ClassRepository classRepository) {
        this.teacherService = teacherService;
        this.attendanceService = attendanceService;
        this.service = service;
        this.classRepository= classRepository;
        this.excelService = excelService;
        
    }


    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<TeacherDTO> registerTeacher(@RequestBody TeacherDTO dto) {
        TeacherDTO saved = teacherService.registerTeacher(dto);
        return ResponseEntity.ok(saved);
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

    // ---------------- Teacher Attendance ----------------
    @PostMapping("/mark-attendance")
    public ResponseEntity<String> markAttendance(@RequestBody AttendanceDTO dto) {
        attendanceService.createAttendance(dto);
        return ResponseEntity.ok("Attendance marked successfully!");
    }

    @GetMapping("/attendance/{id}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable Integer id) {
        AttendanceDTO dto = attendanceService.getAttendanceById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @GetMapping("/all-marked-attendance")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

  
    @GetMapping("/{teacherId}/classes")
    public ResponseEntity<List<ClassDTO>> getClassesByTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(teacherService.getClassesByTeacher(teacherId));
    }

   

    // ---------------- Teacher Login ----------------
    @PostMapping("/login")
    public ResponseEntity<Teacher> login(@RequestBody LoginRequest req) {
        Teacher teacher = teacherService.authenticateTeacher(req.getPhone(), req.getPassword());
        if (teacher == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().header("Message", "Teacher login successfully").body(teacher);
    }

    public static class LoginRequest {
        private String phone;
        private String password;

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // ✅ Get students assigned to teacher
    @GetMapping("/{teacherId}/students")
    public ResponseEntity<List<UserDTO>> getStudentsByTeacher(
            @PathVariable Integer teacherId) {

        return ResponseEntity.ok(
                teacherService.getStudentsByTeacher(teacherId)
        );
    }

    // ---------------- Upload Excel ----------------
    @PostMapping("/upload-excel")
    public ResponseEntity<UploadSummaryDTO> uploadExcel(@RequestParam("file") MultipartFile file) {
        UploadSummaryDTO dto = new UploadSummaryDTO();
        if (file == null || file.isEmpty()) {
            dto.addError("No file uploaded");
            return ResponseEntity.badRequest().body(dto);
        }
        try {
            return ResponseEntity.ok(excelService.importFromExcel(file));
        } catch (Exception e) {
            dto.addError(e.getMessage());
            return ResponseEntity.internalServerError().body(dto);
        }
    }
}
