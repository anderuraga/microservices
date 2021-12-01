package com.elorrieta.cloud.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elorrieta.cloud.productservice.entity.Product;
import com.elorrieta.cloud.productservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping( method = RequestMethod.GET )
	public ResponseEntity<List<Product>> getAll(@RequestParam(name = "catId", required = false) Integer catId) {
		List<Product> products = null;
		if (catId == null) {
			products = productService.getAll();
		} else {
			products = productService.getAllByCatId(catId);
		}
		if (products.isEmpty()) {
			return ResponseEntity.noContent().build(); // 204
		}
		return ResponseEntity.ok(products); // 200
	}

	@RequestMapping( method = RequestMethod.GET , path = "{id}")	
	public ResponseEntity<Product> getById(@PathVariable("id") int id) {
		Product p = productService.findById(id);
		if (p == null) {
			return ResponseEntity.notFound().build(); // 204
		}
		return ResponseEntity.ok(p); // 200
	}
	
	@RequestMapping( method = RequestMethod.DELETE , path = "{id}")	
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		productService.delete(id);		
		return ResponseEntity.status(HttpStatus.OK).build(); // 200, empty body
	}
	
	
	@RequestMapping( method = RequestMethod.POST)	
	public ResponseEntity<Product> save(@RequestBody Product product) {
		Product p = productService.save(product);		
		return ResponseEntity.ok(p); // 200
	}
	
	
	@RequestMapping( method = RequestMethod.PUT, path = "{id}")	
	public ResponseEntity<Product> update(@RequestBody Product product,@PathVariable("id") int id) {
		
		product.setId(id);
		Product p = productService.save(product);		
		return ResponseEntity.ok(p); // 200
	}


}
