package com.lukulabakas.financeTracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lukulabakas.financeTracker.model.Transaction;
import com.lukulabakas.financeTracker.model.TransactionType;
import com.lukulabakas.financeTracker.persistence.TransactionRepository;
import com.lukulabakas.financeTracker.persistence.TransactionSpecifications;

//caontains all logic when handling Transactions
@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepo;
	
	//----- Basic CRUD -----
	//adds new transaction
	public Transaction addTransaction(Transaction transaction) {
		return transactionRepo.save(transaction);
	}
	//returns List of all transactions
	public List<Transaction> getAllTransactions(){
		return transactionRepo.findAll();
	}
	//returns transaction by ID
	public Transaction getTransactionById(int id) {
		return null;
	}
	//updates existing transaction
	public Transaction updateTransaction(int id, Transaction transaction) {
		return null;
	}
	//deletes existing transaction
	public boolean deleteTransaction(int id) {
		return true;
	}

	//----- Filtering and Search -----
	//returns transactions based on date
	public List<Transaction> filterTransactions(String description, TransactionType transactionType, Double amount, LocalDate date, String category){
		Specification<Transaction> spec = TransactionSpecifications.descriptionContains(description)
				.and(TransactionSpecifications.hasTransactionType(transactionType))
				.and(TransactionSpecifications.hasAmount(amount))
				.and(TransactionSpecifications.hasDate(date))
				.and(TransactionSpecifications.categoryContains(category));
		return transactionRepo.findAll(spec);
	}
	
	//----- Statistics -----
	//returns balance of transactions of chosen month
	public 	double sumAllTransactionsByMonth() {
		return 0.0;
	}
	//returns sum of all transactions -> get balance
	public double sumAllTransactions() {
		double sum = 0.0;
		List<Transaction> transactions = transactionRepo.findAll();
		for(Transaction transaction: transactions) {
			sum += transaction.getAmount();
		}
		//to prevent rounding errors etc. and limit to 2 decimal points
		sum = Math.floor(sum * 100) / 100;
		return sum;
	}
}
