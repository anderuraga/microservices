package com.elorrieta.cloud.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elorrieta.cloud.productservice.entity.Category;
import com.elorrieta.cloud.productservice.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> getAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	public Category findById(int id) {
		return categoryRepository.findById(id).orElse(null);
	}

	public Category save(Category product) {
		Category p = categoryRepository.save(product);
		return p;
	}

	public void delete(int id) {
		categoryRepository.deleteById(id);
	}
}
