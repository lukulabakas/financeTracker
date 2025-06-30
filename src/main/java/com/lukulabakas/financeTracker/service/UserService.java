package com.lukulabakas.financeTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lukulabakas.financeTracker.model.User;
import com.lukulabakas.financeTracker.persistence.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//----- Basic CRUD -----
	public User getUserById(int id) {
		return userRepo.findById(id).orElse(null);
	}
	public User getUserByEmail(String email) {
		return userRepo.findUserByEmail(email).orElse(null);
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
	
	//----- Password Service -----
	public void registerUser(String username, String password, String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		
		userRepo.save(user);
	}
	//if login successfull returns the User
	//if login unsuccessfull returns null
	public Optional<User> loginUser(String email, String password) {
		 Optional<User> userOpt = userRepo.findUserByEmail(email);
		 if(userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
			 return userOpt;
		 }else {
			 return Optional.empty();
		 }
	}
	public void changePassword(User user, String newPassword) {
		user.setPassword(newPassword);
		userRepo.save(user);
	}
}
