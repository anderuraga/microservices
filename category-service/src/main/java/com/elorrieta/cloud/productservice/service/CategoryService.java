package com.elorrieta.cloud.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.elorrieta.cloud.productservice.entity.Category;
import com.elorrieta.cloud.productservice.entity.Product;
import com.elorrieta.cloud.productservice.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	RestTemplate restTemplate;

	public List<Category> getAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	public List<Product> getAllProduct(int catId) {
		String url = "http://localhost:8001/product?catId=" + catId;
		List<Product> products = restTemplate.getForObject(url, List.class);
		return products;
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
