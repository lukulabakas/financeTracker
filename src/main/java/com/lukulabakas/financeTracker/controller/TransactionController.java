package com.lukulabakas.financeTracker.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
	
	//----- Basic CRUD -----
	//adds a new transaction
	@PostMapping
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
		return new ResponseEntity<>(transactionService.addTransaction(transaction), HttpStatus.OK);
	}
	//returns all transactions
	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
	}
	//return Transaction by ID
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable int id){
		Transaction transaction = transactionService.getTransactionById(id);
		if(transaction == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
		}
	}
	//update a transaction with given id
	@PutMapping("/{id}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable int id, @RequestBody Transaction transaction){
		Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
		if(updatedTransaction == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
		}
	}
	//deletes a transaction with given id
	//returns 204 when successful
	//returns 404 when not successful
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable int id){
		 boolean deleted = transactionService.deleteTransaction(id);
		 if(deleted) {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
	}
	
	//----- Filtering and Search -----
	//returns filtered List of transactions
	@GetMapping("/filter")
	public ResponseEntity<List<Transaction>> findTransactionsByDate(
			@RequestParam(required = false) String description, 
			@RequestParam(required = false) TransactionType transactionType, 
			@RequestParam(required = false) Double amount,
			@RequestParam(required = false) LocalDate date,
			@RequestParam(required = false) String category
			){
		return new ResponseEntity<>(transactionService.filterTransactions(description, transactionType, amount, date, category), HttpStatus.OK);
	}
	
	//----- Statistics -----
	//sums amounts of all transactions
	@GetMapping("/sum")
	public ResponseEntity<Double> sumAllTransactions() {
		return new ResponseEntity<>(transactionService.sumAllTransactions(), HttpStatus.OK);
	}
	
	//----- ONLY FOR TESTING -----
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
	        return new ResponseEntity<>("Dummy data inserted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Database already contains transactions.", HttpStatus.FORBIDDEN);
	    }
	}
}
