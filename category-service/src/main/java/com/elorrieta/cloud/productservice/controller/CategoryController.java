package com.elorrieta.cloud.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elorrieta.cloud.productservice.entity.Category;
import com.elorrieta.cloud.productservice.entity.Product;
import com.elorrieta.cloud.productservice.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAll() {
		List<Category> categorys = categoryService.getAll();
		if (categorys.isEmpty()) {
			return ResponseEntity.noContent().build(); // 204
		}
		return ResponseEntity.ok(categorys); // 200
	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}")
	public ResponseEntity<Category> getById(@PathVariable("id") int id) {
		Category c = categoryService.findById(id);
		if (c == null) {
			return ResponseEntity.notFound().build(); // 204
		}
		return ResponseEntity.ok(c); // 200
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		categoryService.delete(id);
		return ResponseEntity.ok(id); // 200
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Category category) {
		Category c = categoryService.save(category);
		return ResponseEntity.ok(c); // 200
	}

	@RequestMapping(method = RequestMethod.PUT, path = "{id}")
	public ResponseEntity<Object> update(@RequestBody Category category, @PathVariable("id") int id) {
		category.setId(id);
		categoryService.save(category);
		return ResponseEntity.ok(category); // 200
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{catId}/product")
	public ResponseEntity<List<Product>> getProductsById(@PathVariable("catId") int catId) {
		List<Product> products = categoryService.getAllProduct(catId);
		if (products.isEmpty()) {
			return ResponseEntity.noContent().build(); // 204
		}
		return ResponseEntity.ok(products); // 200
	}

}
