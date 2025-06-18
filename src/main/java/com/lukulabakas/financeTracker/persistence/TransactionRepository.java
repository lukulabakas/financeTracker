package com.lukulabakas.financeTracker.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukulabakas.financeTracker.model.Transaction;

//Repository Interface for Transaction
//allows to use JpaRepository methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
}
