package com.lukulabakas.financeTracker.persistence;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.lukulabakas.financeTracker.model.Transaction;
import com.lukulabakas.financeTracker.model.TransactionType;

//defines the specifications for a Transaction used to filter
public class TransactionSpecifications {
	//checks if a description was given or null, if not null WHERE clause is built
	public static Specification<Transaction> descriptionContains(String description){
        return (transaction, query, criteriaBuilder) ->
        description == null ? null :
        criteriaBuilder.like(criteriaBuilder.lower(transaction.get("description")), "%" + description.toLowerCase() + "%");
	}
	//checks if a transactionType was given or null, if not null WHERE clause is built
	public static Specification<Transaction> hasTransactionType(TransactionType transactionType){
		return (transaction, query, criteriaBuilder) ->
		transactionType == null ? null :
			criteriaBuilder.equal(transaction.get("transactionType"), transactionType);
	}
	//checks if an amount was given or null, if not null WHERE clause is built
	public static Specification<Transaction> hasAmount(Double amount){
		return (transaction, query, criteriaBuilder) ->
		amount == null ? null :
			criteriaBuilder.equal(transaction.get("amount"), amount);
	}
	//checks if a date was given or null, if not null WHERE clause is built
	public static Specification<Transaction> hasDate(LocalDate date){
		return (transaction, query, criteriaBuilder) ->
		date == null ? null:
			criteriaBuilder.equal(transaction.get("date"), date);
	}
	//checks if date is within a given month, if not null WHERE clause is built
	public static Specification<Transaction> hasTimeSpan(LocalDate startDate, LocalDate endDate){
		return (transaction, query, criteriaBuilder) -> {
		return criteriaBuilder.between(transaction.get("date"), startDate, endDate);
		};
	}
	//checks if a category was given or null, if not null WHERE clause is built
	public static Specification<Transaction> categoryContains(String category){
		return (transaction, query, criteriaBuilder) -> 
		category == null ? null :
			criteriaBuilder.like(criteriaBuilder.lower(transaction.get("category")), "%" + category.toLowerCase() + "%");
	}
}
