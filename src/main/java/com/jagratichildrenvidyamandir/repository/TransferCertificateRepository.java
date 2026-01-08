package com.jagratichildrenvidyamandir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jagratichildrenvidyamandir.entity.TransferCertificate;
import java.util.List;

public interface TransferCertificateRepository 
        extends JpaRepository<TransferCertificate, Integer> {

    List<TransferCertificate> findBySessionName(String sessionName);
}
