package com.lukulabakas.financeTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lukulabakas.financeTracker.model.User;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	//----- Basic CRUD -----
	public User getUserById(int id) {
		return null;
	}
	public User addUser(User user) {
		return null;
	}
	public User updateUser(int id, User user) {
		return null;
	}
	public boolean deleteUser(int id) {
		return true;
	}
	public List<User> getAllUsers(){
		return null;
	}
}
