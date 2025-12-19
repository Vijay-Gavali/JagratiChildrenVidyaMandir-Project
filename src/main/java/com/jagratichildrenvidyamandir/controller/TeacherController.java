package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.MarksDTO;
import com.jagratichildrenvidyamandir.dto.TeacherDTO;

import com.jagratichildrenvidyamandir.dto.UploadSummaryDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.ClassEntity;
import com.jagratichildrenvidyamandir.entity.Marks;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.repository.ClassRepository;
import com.jagratichildrenvidyamandir.service.AttendanceService;
import com.jagratichildrenvidyamandir.service.ClassService;
import com.jagratichildrenvidyamandir.service.MarksService;
import com.jagratichildrenvidyamandir.service.TeacherService;
import com.jagratichildrenvidyamandir.service.UserExcelService;
import com.jagratichildrenvidyamandir.service.UserService;

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
    private final ClassService classService;
    private final UserService userService;
    private final UserExcelService excelService;
    private final MarksService marksService;

    public TeacherController(
            TeacherService teacherService,
            AttendanceService attendanceService,
            ClassService classService,
            UserService userService,
            UserExcelService excelService,
            MarksService marksService) {

        this.teacherService = teacherService;
        this.attendanceService = attendanceService;
        this.classService = classService;
        this.userService = userService;
        this.excelService = excelService;
        this.marksService = marksService;
    }

    // ------------------- Teacher CRUD -------------------

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

    // ------------------- Attendance -------------------

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestBody AttendanceDTO dto) {
        attendanceService.createAttendance(dto);
        return ResponseEntity.ok("Attendance marked successfully!");
    }

    @GetMapping("/{id}/attendance")
    public ResponseEntity<AttendanceDTO> getAttendance(@PathVariable Integer id) {
        AttendanceDTO dto = attendanceService.getAttendanceById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @GetMapping("/attendance/all")
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    // ------------------- Class Assignment -------------------

    @PostMapping("/{teacherId}/assign-classes")
    public ResponseEntity<String> assignClasses(
            @PathVariable Integer teacherId,
            @RequestBody List<Integer> classIds) {

        Teacher updated = teacherService.assignClassesToTeacher(teacherId, classIds);
        return ResponseEntity.ok(
                "Classes assigned successfully to teacher ID: " + updated.getTeacherId());
    }

    @GetMapping("/{teacherId}/classes")
    public ResponseEntity<List<ClassDTO>> getClassesByTeacher(
            @PathVariable Integer teacherId) {
        return ResponseEntity.ok(
                teacherService.getClassesByTeacher(teacherId));
    }

    @GetMapping("/{teacherId}/students")
    public ResponseEntity<List<UserDTO>> getStudentsByTeacher(
            @PathVariable Integer teacherId) {
        return ResponseEntity.ok(
                teacherService.getStudentsByTeacher(teacherId));
    }

    // ------------------- Excel Upload -------------------

    @PostMapping("/uploadExcel")
    public ResponseEntity<UploadSummaryDTO> uploadExcel(
            @RequestParam("file") MultipartFile file) {

    // ---------------- Classes & Students ----------------
   

    // GET teacher info + classes by teacherId
    // GET all teachers assigned to a classId
    @GetMapping("/{classId}/teachers")
    public ResponseEntity<List<TeacherDTO>> getTeachersByClass(@PathVariable Integer classId) {
        List<TeacherDTO> teachers = teacherService.getTeachersByClassId(classId);
        if (teachers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{teacherId}/classes")
    public ResponseEntity<List<ClassDTO>> getClassesByTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(teacherService.getClassesByTeacher(teacherId));
    }

    // ------------------- Teacher Login -------------------

    @PostMapping("/login")
    public ResponseEntity<Teacher> login(@RequestBody LoginRequest req) {

        Teacher teacher =
                teacherService.authenticateTeacher(req.getPhone(), req.getPassword());

        if (teacher == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok()
                .header("Message", "Teacher login successfully")
                .body(teacher);
    }

    public static class LoginRequest {
        private String phone;
        private String password;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // ------------------- Marks -------------------

    @PostMapping("/marks/add")
    public Marks addMarks(@RequestBody Marks marks) {
        marks.calculate();
        marks.setCreatedDate(new Date());
        return marksService.saveMarks(marks);
    }

    @PutMapping("/marks/update/{id}")
    public Marks updateMarks(
            @PathVariable Integer id,
            @RequestBody Marks marks) {
        return marksService.updateMarks(id, marks);
    }

    @GetMapping("/marks/all")
    public List<MarksDTO> getAllMarks() {
        return marksService.getAllMarks();
    }
}
