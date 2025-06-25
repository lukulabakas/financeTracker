package com.lukulabakas.financeTracker.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//manages the User Account attributes
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "username")
	private String username;
	//to be edited and ecrypted later
	//as for now for testing password is visibly saved
	private String password;
	@Column(name = "email")
	private String email;
	//a user can have any number of categories
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Category> categories;
	//a user can have any number of transactions
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	/*
	//extra attributes to add Security later
	//determines whether an user is active or inactive
	private boolean enabled;
	//roles of the user
	private Set<Role> roles;
	private boolean locked;
	private String passwordResetToken;
	*/
}
