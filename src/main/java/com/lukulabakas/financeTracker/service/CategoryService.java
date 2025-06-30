package com.lukulabakas.financeTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lukulabakas.financeTracker.model.Category;
import com.lukulabakas.financeTracker.persistence.CategoryRepository;

//contains all logic when handling categories
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	//----- Basic CRUD -----
	//adds a new category
	public Category addCategory(Category category) {
		return categoryRepo.save(category);
	}
	//deletes a category 
	public boolean deleteCategory(int id) {
		Category category = categoryRepo.findById(id).orElse(null);
		if(category == null) {
			return false;
		}else {
			categoryRepo.deleteById(id);
			return true;
		}
	}
	//returns a category by ID or null
	public Category getCategoryById(int id) {
		return categoryRepo.findById(id).orElse(null);
	}
	//update a category by id
	public Category updateCategory(int id, Category category) {
		Category existingCategory = categoryRepo.findById(id).orElse(null);
		if(category.getName() != null) {
			existingCategory.setName(category.getName());
		}
		categoryRepo.save(existingCategory);
		return existingCategory;
	}
	//returns all categories
	public List<Category> getAllCategories(){
		return categoryRepo.findAll();
	}
}
