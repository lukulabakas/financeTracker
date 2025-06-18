package com.lukulabakas.financeTracker.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.lukulabakas.financeTracker.model.Transaction;
import com.lukulabakas.financeTracker.model.TransactionType;
import com.lukulabakas.financeTracker.persistence.TransactionRepository;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	TransactionRepository transactionRepo;
	
	//displays all current transactions
	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		List<Transaction> transactions = transactionRepo.findAll();
		return ResponseEntity.ok(transactions);
	}
	
	//only for testing
	@GetMapping("/dummy")
	public ResponseEntity insertDummyDataOnce() {
	    if (transactionRepo.count() == 0) {
	        transactionRepo.saveAll(Arrays.asList(
	            new Transaction("Salary", TransactionType.INCOME, 3000.0, LocalDate.now(), "category"),
	            new Transaction("Rent", TransactionType.EXPENSE, -900.0, LocalDate.now(), "category"),
	            new Transaction("Netflix", TransactionType.EXPENSE, -12.99, LocalDate.now(), "category"),
	            new Transaction("Spotify", TransactionType.EXPENSE, -13.99, LocalDate.now(), "category")
	        ));
	        return ResponseEntity.ok("Dummy data inserted.");
	    } else {
	        return ResponseEntity.ok("Database already contains transactions.");
	    }
	}

}
