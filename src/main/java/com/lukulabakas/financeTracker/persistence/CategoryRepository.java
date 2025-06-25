package com.lukulabakas.financeTracker.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukulabakas.financeTracker.model.Category;

//Repository Interface for Categories
//allows to use JpaRepository methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
