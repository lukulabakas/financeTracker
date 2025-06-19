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
	public ResponseEntity<String> insertDummyDataOnce() {
	    if (transactionRepo.count() == 0) {
	        transactionRepo.saveAll(Arrays.asList(
	            new Transaction("Salary", TransactionType.INCOME, 3000.0, LocalDate.now(), "monthly"),
	            new Transaction("Rent", TransactionType.EXPENSE, -900.0, LocalDate.now(), ""),
	            new Transaction("Netflix", TransactionType.EXPENSE, -12.99, LocalDate.now(), "Subscription"),
	            new Transaction("Spotify", TransactionType.EXPENSE, -13.99, LocalDate.now(), "Subscription")
	        ));
	        return ResponseEntity.ok("Dummy data inserted.");
	    } else {
	        return ResponseEntity.ok("Database already contains transactions.");
	    }
	}
	
	//sums amounts of all transactions
	@GetMapping("/sum")
	public ResponseEntity<String> sumAllTransactions() {
		double sum = 0;
		List<Transaction> transactions = transactionRepo.findAll();
		for(Transaction transaction: transactions) {
			sum += transaction.getAmount();
		}
		sum = Math.floor(sum * 100)/100;
		return ResponseEntity.ok("Total Sum: " + sum);
	}
	
	//adds a new transaction
	@PostMapping
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
		Transaction newTransaction = transactionRepo.save(transaction);
		return ResponseEntity.ok(newTransaction);
	}

	
	//finds all transactions from a certain date
	@GetMapping("/filter")
	public ResponseEntity<List<Transaction>> findAllTransactionsByDate(@RequestParam LocalDate date){
		List<Transaction> transactions = transactionRepo.findByDate(date);
		return ResponseEntity.ok(transactions);
	}
}
