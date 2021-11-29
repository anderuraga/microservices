package com.elorrieta.cloud.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elorrieta.cloud.productservice.entity.Product;
import com.elorrieta.cloud.productservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getAll(@RequestParam(name = "catId", required = false) Integer catId) {
		List<Product> products = null;
		if (catId == null) {
			products = productService.getAll();
		} else {
			products = productService.getByCatId(catId);
		}
		if (products.isEmpty()) {
			return ResponseEntity.noContent().build(); // 204
		}
		return ResponseEntity.ok(products); // 200
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") int id) {
		Product p = productService.findById(id);
		if (p == null) {
			return ResponseEntity.notFound().build(); // 204
		}
		return ResponseEntity.ok(p); // 200
	}

}
