package com.lukulabakas.financeTracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lukulabakas.financeTracker.model.Transaction;
import com.lukulabakas.financeTracker.persistence.TransactionRepository;

//caontains all logic when handling Transactions
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepo;
	
	//Basic CRUD
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
	public Transaction updateTransaction() {
		return null;
	}
	//deletes existing transaction
	public Transaction deleteTransaction() {
		return null;
	}

	//Filtering and Search
	//returns transactions based on date
	public List<Transaction> findTransactionsByDate(LocalDate date){
		List<Transaction> transactions = transactionRepo.findByDate(date);
		return transactions;
	}
	//returns transactions by Type (EXPENSE or INCOME)
	public List<Transaction> findTransactionsByType(){
		return null;
	}
	//returns transactions by category
	public List<Transaction> findTransactionsByCategory(){
		return null;
	}
	//returns transactions within a date range
	public List<Transaction> findTransactionsByDateRange(){
		return null;
	}
	//Statistics
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
