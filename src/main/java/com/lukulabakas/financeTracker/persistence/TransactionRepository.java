package com.lukulabakas.financeTracker.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lukulabakas.financeTracker.model.Transaction;

//Repository Interface for Transaction
//allows to use JpaRepository methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
public interface TransactionRepository extends JpaRepository<Transaction, Integer>, JpaSpecificationExecutor<Transaction>{
	List<Transaction> findByDate(LocalDate date);
	//sums all transaction amounts within date span
	@Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.date BETWEEN :start AND :end")
	Double sumByDateBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
