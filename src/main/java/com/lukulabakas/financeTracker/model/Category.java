package com.lukulabakas.financeTracker.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//the category of a transaction can be added, deleted and edited by the user
@Entity
@Table(name = "categories")
public class Category {
	//id is generated automatically to uniquely identify a category
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	//a category has a name
	@Column(name = "name")
	private String name;
	//a category always belongs to 
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "category")
	private List<Transaction> transactions;
	
	public Category(String name) {
		this.name = name;
	}
	
	public Category() {
		
	}
	
	public int getId() {return this.id;}
	
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}
	
	@Override
	public String toString() {
		return "Category: [ID: " + id + ", Name: " + name + "]";
	}
}
