package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {
	Optional<SessionEntity> findByName(String name);
}
