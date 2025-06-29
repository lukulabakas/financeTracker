package com.lukulabakas.financeTracker.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukulabakas.financeTracker.model.User;

//Repository Interface for Users
//allows to use JpaRepository methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
public interface UserRepository extends JpaRepository<User, Integer>{
	

}
