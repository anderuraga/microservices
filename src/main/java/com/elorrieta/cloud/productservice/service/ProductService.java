package com.elorrieta.cloud.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elorrieta.cloud.productservice.entity.Product;
import com.elorrieta.cloud.productservice.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAll() {
		return (List<Product>) productRepository.findAll();
	}

	public List<Product> getByCatId(int catId) {
		return productRepository.findByCatId(catId);
	}

	public Product save(Product product) {
		Product p = productRepository.save(product);
		return p;
	}

	public Product findById(int id) {		
		return productRepository.findById(id).orElse(null);
	}
}
