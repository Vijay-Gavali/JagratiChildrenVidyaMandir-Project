package com.jagratichildrenvidyamandir.controller;

import com.jagratichildrenvidyamandir.dto.TransactionDTO;
import com.jagratichildrenvidyamandir.entity.User;
import com.jagratichildrenvidyamandir.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService service;
	@Autowired
    private TransactionService transactionService;

	public TransactionController(TransactionService service) {
		this.service = service;
	}

	// 1. Get all transactions for a specific session - RESTful style
	@GetMapping("/{sessionId}/getAllUsingSessionId")
	public Object getTransactionsBySession(@PathVariable Integer sessionId) {
		try {
			List<TransactionDTO> transactions = service.getTransactionsBySessionId(sessionId);
			return transactions;

		} catch (EntityNotFoundException ex) {
			return Map.of("error", ex.getMessage());
		} catch (Exception ex) {
			return Map.of("error", ex.getMessage());
		}
	}

	// 2. Create transaction for a specific session - sessionId comes from URL
	// (client doesn't send it)
	@PostMapping("/{sessionId}/save")
	public ResponseEntity<?> createTransactionForSession(@PathVariable Integer sessionId,
			@RequestBody TransactionDTO dto) {
		try {
			dto.setSessionId(sessionId); // set sessionId from URL
			TransactionDTO saved = service.createTransaction(dto);
			return ResponseEntity.status(201).body(saved);
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
		}
	}

	// 3. Get all transactions (without session filter)
	@GetMapping("/GetAll")
	public ResponseEntity<?> getAllTransactions() {
		try {
			List<TransactionDTO> transactions = service.getAllTransactions();
			return ResponseEntity.ok(transactions);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
		}
	}

	// 4. Get transaction by ID (standalone)
	@GetMapping("/{id}")
	public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(service.getTransactionById(id));
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
		}
	}

	// 5. Update transaction by ID
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO dto) {
		try {
			TransactionDTO updated = service.updateTransaction(id, dto);
			return ResponseEntity.ok(updated);
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
		}
	}

	// 6. Delete transaction by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
		try {
			service.deleteTransaction(id);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
		}
	}

	// 7. Get transactions by userId (standalone)
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getTransactionsByUser(@PathVariable Integer userId) {
		try {
			List<TransactionDTO> transactions = service.getTransactionsByUserId(userId);
			return ResponseEntity.ok(transactions);
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
		}
	}
	
	// getStudents by the sessionid
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<Map<String, Object>>> getStudentsBySession(@PathVariable Integer sessionId) {
        List<Map<String, Object>> students = transactionService.getStudentsBySessionId(sessionId);
        return ResponseEntity.ok(students);
    }

}
