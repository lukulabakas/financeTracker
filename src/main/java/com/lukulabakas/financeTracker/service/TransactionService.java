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
	private TransactionRepository transactionRepo;
	
	//----- Basic CRUD -----
	//adds new transaction
	public Transaction addTransaction(Transaction transaction) {
		return transactionRepo.save(transaction);
	}
	//returns List of all transactions
	public List<Transaction> getAllTransactions(){
		return transactionRepo.findAll();
	}
	//returns transaction by ID or null
	public Transaction getTransactionById(int id) {
		return transactionRepo.findById(id).orElse(null);
	}
	//updates existing transaction
	//returns null if no transaction with id in table
	//only updates attributes that are not null
	public Transaction updateTransaction(int id, Transaction transaction) {
		Transaction existingTransaction = transactionRepo.findById(id).orElse(null);
		if(existingTransaction == null) {
			return null;
		}else {
			if(transaction.getDescription() != null) {
				existingTransaction.setDescription(transaction.getDescription());
			}
			if(transaction.getTransactionType() != null) {
				existingTransaction.setTransactionType(transaction.getTransactionType());
			}
			if(transaction.getAmount() != null) {
				existingTransaction.setAmount(transaction.getAmount());
			}
			if(transaction.getDate() != null) {
				existingTransaction.setDate(transaction.getDate());
			}
			if(transaction.getCategory() != null) {
				existingTransaction.setCategory(transaction.getCategory());
			}
			transactionRepo.save(existingTransaction);
			return existingTransaction;
		}
	}
	//deletes existing transaction
	public boolean deleteTransaction(int id) {
		Transaction transaction = transactionRepo.findById(id).orElse(null);
		if(transaction == null) {
			return false;
		}else {
			transactionRepo.deleteById(id);
			return true;
		}
	}

	//----- Filtering and Search -----
	//returns transactions based on date (one fixed day as date parameter)
	public List<Transaction> filterTransactions(String description, TransactionType transactionType, Double amount, LocalDate date, String category){
		Specification<Transaction> spec = TransactionSpecifications.descriptionContains(description)
				.and(TransactionSpecifications.hasTransactionType(transactionType))
				.and(TransactionSpecifications.hasAmount(amount))
				.and(TransactionSpecifications.hasDate(date))
				.and(TransactionSpecifications.categoryContains(category));
		return transactionRepo.findAll(spec);
	}
	//returns transactions based on filter criteria (start and end date for time span as date parameters)
	public List<Transaction> filterTransactions(String description, TransactionType transactionType, Double amount, LocalDate startDate, LocalDate endDate, String category){
		Specification<Transaction> spec = TransactionSpecifications.descriptionContains(description)
				.and(TransactionSpecifications.hasTransactionType(transactionType))
				.and(TransactionSpecifications.hasAmount(amount))
				.and(TransactionSpecifications.hasTimeSpan(startDate, endDate))
				.and(TransactionSpecifications.categoryContains(category));
		return transactionRepo.findAll(spec);
	}
	
	//----- Statistics -----
	//returns sum of all transactions of given month
	public Double getMonthlySum(int year, int month) {
		//set first day of month
		LocalDate startDate = LocalDate.of(year, month, 1);
		//set last day of month
		LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
		Specification<Transaction> spec = TransactionSpecifications.hasTimeSpan(startDate, endDate);
		List<Transaction> transactions = transactionRepo.findAll(spec);
		Double sum = 0.0;
		for(Transaction transaction : transactions) {
			sum += transaction.getAmount();
		}
		return sum;
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
