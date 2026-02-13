package com.jagratichildrenvidyamandir.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jagratichildrenvidyamandir.dto.AttendanceDTO;
import com.jagratichildrenvidyamandir.entity.Attendance;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository repository;

    public AttendanceService(AttendanceRepository repository) {
        this.repository = repository;
    }

    /* ================= CREATE ================= */
    public AttendanceDTO createAttendance(AttendanceDTO dto) {

        Attendance attendance = new Attendance();
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());

        User user = new User();
        user.setUserId(dto.getUserId());
        attendance.setUser(user);

        Attendance saved = repository.save(attendance);
        return mapToDTO(saved);
    }

    /* ================= GET BY ID ================= */
    public AttendanceDTO getAttendanceById(Integer id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    /* ================= GET ALL ================= */
    public List<AttendanceDTO> getAllAttendance() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /* ================= UPDATE ================= */
    public AttendanceDTO updateAttendance(Integer id, AttendanceDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setStatus(dto.getStatus());
                    Attendance updated = repository.save(existing);
                    return mapToDTO(updated);
                })
                .orElse(null);
    }

    /* ================= DELETE ================= */
    public boolean deleteAttendance(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    /* ================= USER ATTENDANCE ================= */
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getAttendanceByUserId(Integer userId) {
        return repository.findByUserUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /* ================= CHECK ATTENDANCE EXISTS (CLASS + DATE) ================= */
    public boolean isAttendanceMarkedForClass(Integer classId, LocalDate date) {
        return repository.existsByUser_StudentClass_ClassIdAndDate(classId, date);
    }

    /* ================= GET ATTENDANCE BY CLASS + DATE ================= */
    public List<AttendanceDTO> getAttendanceByClassAndDate(Integer classId, LocalDate date) {

        return repository.findByUser_StudentClass_ClassIdAndDate(classId, date)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /* ================= MARK ATTENDANCE (BULK) ================= */
    @Transactional
    public List<AttendanceDTO> markAttendanceBulk(List<AttendanceDTO> dtoList) {

        if (dtoList == null || dtoList.isEmpty()) {
            return List.of();
        }

        Integer classId = dtoList.get(0).getClassId();
        if (classId == null) {
            throw new RuntimeException("ClassId missing in request");
        }

        LocalDate date = dtoList.get(0).getDate();
        if (date == null) {
            date = LocalDate.now();
        }

        boolean alreadyMarked = repository.existsByUser_StudentClass_ClassIdAndDate(classId, date);

        if (alreadyMarked) {
            return repository.findByUser_StudentClass_ClassIdAndDate(classId, date)
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        }

        LocalDate finalDate = date;

        List<Attendance> attendanceList = dtoList.stream().map(dto -> {

            Attendance attendance = new Attendance();
            attendance.setDate(finalDate);
            attendance.setStatus(dto.getStatus());

            User user = new User();
            user.setUserId(dto.getUserId());
            attendance.setUser(user);

            return attendance;

        }).collect(Collectors.toList());

        repository.saveAll(attendanceList);

        return repository.findByUser_StudentClass_ClassIdAndDate(classId, finalDate)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /* ================= TODAY ATTENDANCE ================= */
    @Transactional(readOnly = true)
    public List<AttendanceDTO> getTodayAttendance() {

        LocalDate today = LocalDate.now();

        return repository.findByDate(today)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /* ================= EDIT ATTENDANCE ================= */
    @Transactional
    public AttendanceDTO editAttendance(Integer attendanceId, String status) {

        Attendance attendance = repository.findById(attendanceId).orElse(null);

        if (attendance == null) return null;

        attendance.setStatus(status);

        Attendance updated = repository.save(attendance);

        return mapToDTO(updated);
    }

    
    /* ================= SAVE OR UPDATE SINGLE ============== */
  
    @Transactional
    public AttendanceDTO saveOrUpdateAttendance(AttendanceDTO dto) {

        if (dto.getUserId() == null || dto.getDate() == null || dto.getStatus() == null) {
            throw new RuntimeException("userId/date/status missing");
        }

        Attendance existing = repository.findByUser_UserIdAndDate(dto.getUserId(), dto.getDate());

        if (existing != null) {
            existing.setStatus(dto.getStatus());
            Attendance updated = repository.save(existing);
            return mapToDTO(updated);
        }

        Attendance attendance = new Attendance();
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());

        User user = new User();
        user.setUserId(dto.getUserId());
        attendance.setUser(user);

        Attendance saved = repository.save(attendance);

        return mapToDTO(saved);
    }

    /* ================= DTO MAPPER ================= */
    private AttendanceDTO mapToDTO(Attendance attendance) {

        AttendanceDTO dto = new AttendanceDTO();
        dto.setAttendanceId(attendance.getAttendanceId());
        dto.setDate(attendance.getDate());
        dto.setStatus(attendance.getStatus());

        if (attendance.getUser() != null) {

            dto.setUserId(attendance.getUser().getUserId());
            dto.setStudentName(attendance.getUser().getName());

            if (attendance.getUser().getStudentClass() != null) {
                dto.setClassName(attendance.getUser().getStudentClass().getClassName());
                dto.setClassId(attendance.getUser().getStudentClass().getClassId());
            }
        }

        return dto;
    }
}
