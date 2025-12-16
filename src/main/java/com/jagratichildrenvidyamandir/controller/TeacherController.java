package com.jagratichildrenvidyamandir.controller;

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

    @PostMapping("/register")
    public ResponseEntity<TeacherDTO> registerTeacher(@RequestBody TeacherDTO dto) {
        return new ResponseEntity<>(teacherService.registerTeacher(dto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(
            @PathVariable Integer id,
            @RequestBody TeacherDTO dto) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully.");
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
