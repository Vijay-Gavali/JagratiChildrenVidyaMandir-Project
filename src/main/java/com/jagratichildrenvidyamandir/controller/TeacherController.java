package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.controller.UserController.LoginRequest;
import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.dto.ClassDTO;
import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.entity.Teacher;
import com.jagratichildrenvidyamandir.service.AttendanceService;
import com.jagratichildrenvidyamandir.service.ClassService;
import com.jagratichildrenvidyamandir.service.TeacherService;
import com.jagratichildrenvidyamandir.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final AttendanceService attendanceService;
    @Autowired
    private ClassService classService;
    private final UserService service;

    public TeacherController(TeacherService teacherService, AttendanceService attendanceService,ClassService classService,UserService service) {
        this.teacherService = teacherService;
        this.attendanceService = attendanceService;
        this. classService=classService;
        this.service=service;
    }
    
   


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
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO dto) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully.");
    }

   

    // Mark attendance
    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestBody AttendanceDTO dto) {
        attendanceService.createAttendance(dto);
        return ResponseEntity.ok("Attendance marked successfully!");
    }
    
   

    // ------------------- Assign classes later -------------------
    @PostMapping("/{teacherId}/assign-classes")
    public ResponseEntity<String> assignClasses(
            @PathVariable Integer teacherId,
            @RequestBody List<Integer> classIds) {

        Teacher updated = teacherService.assignClassesToTeacher(teacherId, classIds);
        return ResponseEntity.ok("Classes assigned successfully to teacher ID: " + updated.getTeacherId());
    }

    // ------------------- Get classes of a teacher -------------------
    @GetMapping("/{teacherId}/classes")
    public ResponseEntity<List<ClassDTO>> getClassesByTeacher(@PathVariable Integer teacherId) {
        List<ClassDTO> classes = teacherService.getClassesByTeacher(teacherId);
        return ResponseEntity.ok(classes);
    }
    //----getstudentlistt
    @GetMapping("/{teacherId}/students")
    public ResponseEntity<List<UserDTO>> getStudentsByTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(teacherService.getStudentsByTeacher(teacherId));
    }
    @GetMapping("/{id}/oneattendave")
    public ResponseEntity<AttendanceDTO> get(@PathVariable Integer id) {
        AttendanceDTO dto = attendanceService.getAttendanceById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @GetMapping("/getallmarkedattendance")
    public List<AttendanceDTO> getAll() {
        return attendanceService.getAllAttendance();
    }

///loginteacher
///
    @PostMapping("/login")
    public ResponseEntity<Teacher> login(@RequestBody LoginRequest req) {
        Teacher teacher = teacherService.authenticateTeacher(req.getPhone(), req.getPassword());
        return teacher == null ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() : ResponseEntity.ok(teacher);
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
}

