package com.jagratichildrenvidyamandir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jagratichildrenvidyamandir.entity.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByPaymentMode(String paymentMode);
	List<Transaction> findByStatus(String status);
	Transaction findByReceiptNumber(String receiptNumber);
	
    List<Transaction> findByUserUserId(Integer userId);
	List<Transaction> findBySessionSessionId(Integer sessionId);

}