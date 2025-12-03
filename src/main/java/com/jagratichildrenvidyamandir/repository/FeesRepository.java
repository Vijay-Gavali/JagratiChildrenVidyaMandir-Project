package com.jagratichildrenvidyamandir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jagratichildrenvidyamandir.entity.Fees;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Integer> {

	 @Query("SELECT f FROM Fees f WHERE f.user.userId = :userId")
	    List<Fees> findByUserId(@Param("userId") Integer userId);
	    
}
