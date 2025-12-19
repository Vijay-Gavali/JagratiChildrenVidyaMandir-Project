package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    
    @Query("SELECT n FROM Notice n ORDER BY n.date DESC")
    List<Notice> findAllOrderByDateDesc();
    
    List<Notice> findBySessionSessionIdOrderByDateDesc(Integer sessionId);
}