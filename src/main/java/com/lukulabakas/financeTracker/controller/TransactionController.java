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
import com.lukulabakas.financeTracker.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	@Autowired
	TransactionRepository transactionRepo;
	@Autowired
	TransactionService transactionService;
	
	//Basic CRUD
	//adds a new transaction
	@PostMapping
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
		return ResponseEntity.ok(transactionService.addTransaction(transaction));
	}
	//returns all transactions
	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		return  ResponseEntity.ok(transactionService.getAllTransactions());
	}
	
	//Filtering and Search
	//finds all transactions from a certain date
	//?date=yyyy-mm-dd
	@GetMapping("/filter")
	public ResponseEntity<List<Transaction>> findTransactionsByDate(@RequestParam LocalDate date){
		return ResponseEntity.ok(transactionService.findTransactionsByDate(date));
	}
	
	//Statistics
	//sums amounts of all transactions
	@GetMapping("/sum")
	public ResponseEntity<Double> sumAllTransactions() {
		return ResponseEntity.ok(transactionService.sumAllTransactions());
	}
	
	//ONLY FOR TESTING
	//adds dummy transactions
	//only adds transactions if there are no transactions saved yet
	@GetMapping("/dummy")
	public ResponseEntity<String> insertDummyData() {
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
}
