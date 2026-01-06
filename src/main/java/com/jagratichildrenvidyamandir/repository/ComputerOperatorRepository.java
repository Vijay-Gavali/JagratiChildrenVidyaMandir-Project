package com.jagratichildrenvidyamandir.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jagratichildrenvidyamandir.entity.ComputerOperator;

public interface ComputerOperatorRepository 
        extends JpaRepository<ComputerOperator, Integer> {

    Optional<ComputerOperator> findByPhoneNumber(String phoneNumber);

    Optional<ComputerOperator> findByPhoneNumberAndPassword(
            String phoneNumber, String password);
}

