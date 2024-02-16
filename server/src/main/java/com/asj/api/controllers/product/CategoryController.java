package com.asj.api.controllers.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.product.CategoryModel;
import com.asj.api.services.product.CategoryService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<CategoryModel>> getAllCategories() {
		List<CategoryModel> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Integer id) {
		CategoryModel category = categoryService.getCategoryById(id);
		return ResponseEntity.ok(category);
	}

	@PostMapping
	public ResponseEntity<CategoryModel> createCategory(@Valid @RequestBody CategoryModel category,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		CategoryModel createdCategory = categoryService.createCategory(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryModel> updateCategory(@PathVariable Integer id,
			@Valid @RequestBody CategoryModel category, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		CategoryModel updatedCategory = categoryService.updateCategory(id, category);
		return ResponseEntity.ok(updatedCategory);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CategoryModel> patchCategory(@PathVariable Integer id,
			@Valid @RequestBody CategoryModel category, BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, category);
		CategoryModel patchedCategory = categoryService.patchCategory(id, category);
		return ResponseEntity.ok(patchedCategory);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryModel> deleteCategory(@PathVariable Integer id) {
		CategoryModel deletedCategory = categoryService.deleteCategory(id);
		return ResponseEntity.ok(deletedCategory);
	}
}
