package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Integer> {
    List<Marks> findByTeacherId(Integer teacherId);
    List<Marks> findByClassId(Integer classId);
    List<Marks> findByStudentId(Integer studentId);
}
