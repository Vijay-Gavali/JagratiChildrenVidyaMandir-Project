package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.dto.TeacherDTO;
import com.jagratichildrenvidyamandir.dto.UserDTO;
import com.jagratichildrenvidyamandir.service.AttendanceService;
import com.jagratichildrenvidyamandir.service.TeacherService;
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

    public TeacherController(TeacherService teacherService, AttendanceService attendanceService) {
        this.teacherService = teacherService;
        this.attendanceService = attendanceService;
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
}

