package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.controller.UserController.LoginRequest;
import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.MarksDTO;
import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.dto.UploadSummaryDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.Marks;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.service.AttendanceService;
import com.jagratichildrenvidyamandir.service.ClassService;
import com.jagratichildrenvidyamandir.service.MarksService;
import com.jagratichildrenvidyamandir.service.TeacherService;
import com.jagratichildrenvidyamandir.service.UserExcelService;
import com.jagratichildrenvidyamandir.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final AttendanceService attendanceService;
    private final ClassService classService;
    private final UserService service;
    private final UserExcelService excelService;
    private final MarksService marksService;
    private final String BASE_DIR = "uploads/teachers/";


    @Autowired
    public TeacherController(TeacherService teacherService, AttendanceService attendanceService,
                             ClassService classService, UserService service,
                             UserExcelService excelService, MarksService marksService) {
        this.teacherService = teacherService;
        this.attendanceService = attendanceService;
        this.classService = classService;
        this.service = service;
        this.excelService = excelService;
        this.marksService = marksService;
    }

    // ---------------- Teacher CRUD ----------------
    @PostMapping("/register")
    public ResponseEntity<TeacherDTO> registerTeacher(@RequestBody TeacherDTO dto) {
        return new ResponseEntity<>(teacherService.registerTeacher(dto), HttpStatus.CREATED);
    }
  



    @GetMapping("/all-teachers")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO dto) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully.");
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

    // ---------------- Classes & Students ----------------
    @PostMapping("/{teacherId}/assign-classes")
    public ResponseEntity<String> assignClasses(@PathVariable Integer teacherId, @RequestBody List<Integer> classIds) {
        Teacher updated = teacherService.assignClassesToTeacher(teacherId, classIds);
        return ResponseEntity.ok("Classes assigned successfully to teacher ID: " + updated.getTeacherId());
    }

    @GetMapping("/{teacherId}/classes")
    public ResponseEntity<List<ClassDTO>> getClassesByTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(teacherService.getClassesByTeacher(teacherId));
    }

    @GetMapping("/{teacherId}/students")
    public ResponseEntity<List<UserDTO>> getStudentsByTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(teacherService.getStudentsByTeacher(teacherId));
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

    // ---------------- Marks Management ----------------
    @PostMapping("/marks/add")
    public ResponseEntity<Marks> addMarks(@RequestBody Marks marks) {
        return ResponseEntity.status(HttpStatus.CREATED).body(marksService.saveMarks(marks));
    }

    @PutMapping("/marks/update/{id}")
    public ResponseEntity<Marks> updateMarks(@PathVariable Integer id, @RequestBody Marks marks) {
        return ResponseEntity.ok(marksService.updateMarks(id, marks));
    }

    @GetMapping("/marks/all")
    public ResponseEntity<List<MarksDTO>> getAllMarks() {
        return ResponseEntity.ok(marksService.getAllMarks());
    }

    @GetMapping("/marks/student/{studentId}")
    public ResponseEntity<List<MarksDTO>> getMarksByStudent(@PathVariable Integer studentId) {
        return ResponseEntity.ok(marksService.getMarksByStudent(studentId));
    }

    @GetMapping("/marks/class/{classId}")
    public ResponseEntity<List<MarksDTO>> getMarksByClass(@PathVariable Integer classId) {
        return ResponseEntity.ok(marksService.getMarksByClass(classId));
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
