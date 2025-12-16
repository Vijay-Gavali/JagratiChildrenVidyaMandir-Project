package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Integer> {

    List<Marks> findByStudentId(Integer studentId);

    List<Marks> findByStudentIdAndExamType(Integer studentId, String examType);
}
