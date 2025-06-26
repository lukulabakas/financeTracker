package com.lukulabakas.financeTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lukulabakas.financeTracker.model.User;
import com.lukulabakas.financeTracker.persistence.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	//----- Basic CRUD -----
	public User getUserById(int id) {
		return userRepo.findById(id).orElse(null);
	}
	public User addUser(User user) {
		return userRepo.save(user);
	}
	public User updateUser(int id, User user) {
		User existingUser = userRepo.findById(id).orElse(null);
		if(existingUser == null) {
			return null;
		}else {
			if(user.getUsername() != null) {
				existingUser.setUsername(user.getUsername());
			}
			if(user.getPassword() != null) {
				existingUser.setPassword(user.getPassword());
			}
			if(user.getEmail() != null) {
				existingUser.setEmail(user.getPassword());
			}
			userRepo.save(existingUser);
			return existingUser;
		}
	}
	public boolean deleteUser(int id) {
		User user = userRepo.findById(id).orElse(null);
		if(user == null) {
			return false;
		}else {
			userRepo.deleteById(id);
			return true;
		}
	}
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
}
