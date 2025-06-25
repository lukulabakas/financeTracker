package com.lukulabakas.financeTracker.model;

import java.time.LocalDate;

import jakarta.persistence.*;

//each entry is saved as a transaction
@Entity
@Table(name = "transactions")
public class Transaction {
	//id is generated automatically to uniquely identify transactions
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	//user can set a description for his transaction
	@Column(name = "description")
	private String description;
	//ENUM: INCOME or EXPENSE
	@Column(name = "transactionType")
	private TransactionType transactionType;
	//either positive or negative amount
	@Column(name = "amount")
	private Double amount;
	//date of transaction (not of transaction creation)
	@Column(name = "date")
	private LocalDate date;
	@ManyToOne
	//a transaction always belongs to one unique user
	@JoinColumn(name = "user_id")
	private User user;
	//user can choose a category (e.g. groceries)
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Transaction(String description, TransactionType transactionType, double amount, LocalDate date, Category category) {
		this.transactionType = transactionType;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.category = category;
	}
	
	public Transaction() {
		
	}
	
	public int getId() {return this.id;}
	
	public void setDescription(String description) {this.description = description;}
	public String getDescription() {return this.description;}
	
	public void setTransactionType(TransactionType transactionType) {this.transactionType = transactionType;}
	public TransactionType getTransactionType() {return this.transactionType;}
	
	public void setAmount(double amount) {this.amount = amount;}
	public Double getAmount() {return this.amount;}
	
	public void setDate(LocalDate date) {this.date = date;}
	public LocalDate getDate() {return this.date;}
	
	public void setCategory(Category category) {this.category = category;}
	public Category getCategory() {return this.category;}
	
	@Override
	public String toString() {
		return "Transaction [ID: " + id + ", Description: " + description + ", TransactionType: " + transactionType + ", Amount: " + amount + ", Date: " + date + ", Category: " + category + "]";
	}
}
