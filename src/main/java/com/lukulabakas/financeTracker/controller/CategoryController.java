package com.lukulabakas.financeTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukulabakas.financeTracker.model.Category;
import com.lukulabakas.financeTracker.persistence.CategoryRepository;
import com.lukulabakas.financeTracker.service.CategoryService;

//Controller for the user created categories of transactions
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	CategoryService categoryService;
	
	//----- Basic CRUD -----
	//adds a new category
	@PostMapping
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK);
	}
	//deletes a category 
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable int id){
		boolean deleted = categoryService.deleteCategory(id);
		if(deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//returns a category by ID or null
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable int id){
		Category category = categoryService.getCategoryById(id);
		if(category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(category, HttpStatus.OK);
		}
	}
	//update a category by id
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable int id){
		Category category = categoryService.getCategoryById(id);
		if(category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
		}
	}
	//returns all categories
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories(){
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}
}
