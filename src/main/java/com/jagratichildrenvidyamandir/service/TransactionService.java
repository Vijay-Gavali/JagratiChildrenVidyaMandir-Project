package com.jagratichildrenvidyamandir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jagratichildrenvidyamandir.dto.TransactionDTO;
import com.jagratichildrenvidyamandir.entity.SessionEntity;
import com.jagratichildrenvidyamandir.entity.Transaction;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.mapper.TransactionMapper;
import com.jagratichildrenvidyamandir.repository.SessionRepository;
import com.jagratichildrenvidyamandir.repository.TransactionRepository;
import com.jagratichildrenvidyamandir.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionMapper transactionMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Transactional
	public TransactionDTO createTransaction(TransactionDTO requestDTO) {
		// 1) validate userId presence
		Integer userId = requestDTO.getUserId();
		if (userId == null) {
			throw new RuntimeException("userId is required to create a transaction.");
		}
		// 2) fetch user (validate)
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

		// 3) map DTO -> Entity
		Transaction transaction = transactionMapper.toEntity(requestDTO);

		transaction.setUser(user);

		Integer sessionId = requestDTO.getSessionId();
		if (sessionId == null) {
			throw new RuntimeException("sessionId is required to create a transaction.");
		}

		SessionEntity entity = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new RuntimeException("session not found with id: " + sessionId));
		transaction.setSession(entity);

		if (transaction.getPaymentDate() == null) {
			transaction.setPaymentDate(LocalDateTime.now());
		}
		if (transaction.getStatus() == null) {
			transaction.setStatus("SUCCESS");
		}

		Transaction saved = transactionRepository.save(transaction);

		return transactionMapper.toDto(saved);
	}

	public List<TransactionDTO> getAllTransactions() {
		List<Transaction> transactions = transactionRepository.findAll();
		return transactions.stream().map(transactionMapper::toDto).collect(Collectors.toList());
	}

	public TransactionDTO getTransactionById(Long id) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
		return transactionMapper.toDto(transaction);
	}

	public TransactionDTO getTransactionByTransactionId(String transactionId) {
		Transaction transaction = transactionRepository.findByTransactionId(transactionId);
		if (transaction == null) {
			throw new RuntimeException("Transaction not found with ID: " + transactionId);
		}
		return transactionMapper.toDto(transaction);
	}

	@Transactional
	public TransactionDTO updateTransactionStatus(Long id, String status) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));

		transaction.setStatus(status);
		Transaction updatedTransaction = transactionRepository.save(transaction);

		return transactionMapper.toDto(updatedTransaction);
	}

	@Transactional
	public TransactionDTO updateTransaction(Long id, TransactionDTO requestDTO) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));

		// Update entity with DTO data
		transactionMapper.updateEntity(requestDTO, transaction);

		Transaction updatedTransaction = transactionRepository.save(transaction);

		return transactionMapper.toDto(updatedTransaction);
	}

	@Transactional
	public void deleteTransaction(Long id) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
		transactionRepository.deleteById(id);
	}

	public List<TransactionDTO> getTransactionsByStatus(String status) {
		List<Transaction> transactions = transactionRepository.findByStatus(status);
		return transactions.stream().map(transactionMapper::toDto).collect(Collectors.toList());
	}

	public List<TransactionDTO> getTransactionsByPaymentMode(String paymentMode) {
		List<Transaction> transactions = transactionRepository.findByPaymentMode(paymentMode);
		return transactions.stream().map(transactionMapper::toDto).collect(Collectors.toList());
	}

	public Double getTotalTransactionAmount() {
		List<Transaction> transactions = transactionRepository.findAll();
		return transactions.stream().filter(t -> "SUCCESS".equals(t.getStatus())).mapToDouble(Transaction::getAmount)
				.sum();
	}

	public Double getTotalTransactionAmountByStatus(String status) {
		List<Transaction> transactions = transactionRepository.findByStatus(status);
		return transactions.stream().mapToDouble(Transaction::getAmount).sum();
	}

	public Long getTransactionCountByStatus(String status) {
		List<Transaction> transactions = transactionRepository.findByStatus(status);
		return (long) transactions.size();
	}

	public List<TransactionDTO> getRecentTransactions(int limit) {
		List<Transaction> transactions = transactionRepository.findAll();
		return transactions.stream().sorted((t1, t2) -> t2.getCreatedAt().compareTo(t1.getCreatedAt())).limit(limit)
				.map(transactionMapper::toDto).collect(Collectors.toList());
	}

	public List<TransactionDTO> getTransactionsByUserId(Integer userId) {
		List<Transaction> transactions = transactionRepository.findByUserUserId(userId);
		// map entity -> dto using the injected transactionMapper (instance method
		// toDto)
		return transactions.stream().map(transactionMapper::toDto) // correct method name; instance method
				.collect(Collectors.toList()); // safer than .toList() for older Java
	}

	public List<TransactionDTO> getTransactionsBySessionId(Integer sessionId) {
		// 1. Validate session exists
		SessionEntity session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new EntityNotFoundException("Session not found with id: " + sessionId));

		// 2. Get all transactions belonging to this session
		List<Transaction> transactions = transactionRepository.findBySessionSessionId(sessionId);

		// 3. Convert to DTO
		return transactions.stream().map(transactionMapper::toDto).toList();
	}

}