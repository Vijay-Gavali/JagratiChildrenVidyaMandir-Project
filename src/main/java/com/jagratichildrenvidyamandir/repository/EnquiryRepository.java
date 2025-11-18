package com.jagratichildrenvidyamandir.repository;

import com.jagratichildrenvidyamandir.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {
    // add custom queries if required
}
