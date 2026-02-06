package com.jagratichildrenvidyamandir.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jagratichildrenvidyamandir.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByUserUserId(Integer userId);

    boolean existsByUser_UserIdAndDate(Integer userId, LocalDate date);

    // fetch today's attendance
    List<Attendance> findByDate(LocalDate date);

    // ✅ NEW: check attendance exists for class + date
    boolean existsByUser_StudentClass_ClassIdAndDate(Integer classId, LocalDate date);

    // ✅ NEW: fetch attendance by class + date (for edit + view)
    List<Attendance> findByUser_StudentClass_ClassIdAndDate(Integer classId, LocalDate date);
}
