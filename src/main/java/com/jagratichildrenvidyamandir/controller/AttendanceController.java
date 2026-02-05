package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService service) {
        this.attendanceService = service;
    }

    @PostMapping
    public ResponseEntity<AttendanceDTO> create(@RequestBody AttendanceDTO dto) {
        return new ResponseEntity<>(attendanceService.createAttendance(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDTO> get(@PathVariable Integer id) {
        AttendanceDTO dto = attendanceService.getAttendanceById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<AttendanceDTO> getAll() {
        return attendanceService.getAllAttendance();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDTO> update(@PathVariable Integer id, @RequestBody AttendanceDTO dto) {
        AttendanceDTO updated = attendanceService.updateAttendance(id, dto);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return attendanceService.deleteAttendance(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByUserId(@PathVariable Integer userId) {
        List<AttendanceDTO> list = attendanceService.getAttendanceByUserId(userId);
        return ResponseEntity.ok(list);
    }

    // ================= MARK ATTENDANCE (BULK) =================
    @PostMapping("/mark")
    public ResponseEntity<List<AttendanceDTO>> markAttendance(
            @RequestBody List<AttendanceDTO> dtoList) {

        return ResponseEntity.ok(
                attendanceService.markAttendanceBulk(dtoList)
        );
    }

    // ================= TODAY ATTENDANCE =================
    @GetMapping("/today")
    public ResponseEntity<List<AttendanceDTO>> getTodayAttendance() {
        return ResponseEntity.ok(attendanceService.getTodayAttendance());
    }

    // ================= GET ATTENDANCE BY CLASS + DATE =================
    @GetMapping("/class/{classId}/date/{date}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByClassAndDate(
            @PathVariable Integer classId,
            @PathVariable String date
    ) {
        LocalDate parsedDate = LocalDate.parse(date);
        return ResponseEntity.ok(attendanceService.getAttendanceByClassAndDate(classId, parsedDate));
    }

    // ================= CHECK ALREADY MARKED =================
    @GetMapping("/check/{classId}/{date}")
    public ResponseEntity<Boolean> checkAttendanceMarked(
            @PathVariable Integer classId,
            @PathVariable String date
    ) {
        LocalDate parsedDate = LocalDate.parse(date);
        return ResponseEntity.ok(attendanceService.isAttendanceMarkedForClass(classId, parsedDate));
    }

    // ================= EDIT ATTENDANCE =================
    @PutMapping("/edit/{attendanceId}")
    public ResponseEntity<AttendanceDTO> editAttendance(
            @PathVariable Integer attendanceId,
            @RequestBody AttendanceDTO dto
    ) {

        AttendanceDTO updated = attendanceService.editAttendance(attendanceId, dto.getStatus());

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }
}
