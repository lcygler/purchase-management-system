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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.product.ProductModel;
import com.asj.api.services.product.ProductService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<ProductModel>> getAllProducts() {
		List<ProductModel> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@GetMapping(params = "supplierId") // [GET] /products?supplierId={id}
	public ResponseEntity<List<ProductModel>> getProductsBySupplier(@RequestParam Integer supplierId) {
		List<ProductModel> products = productService.getProductsBySupplier(supplierId);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductModel> getProductById(@PathVariable Integer id) {
		ProductModel product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/sku")
	public ResponseEntity<String> getProductSku() {
		String sku = productService.generateProductSku();
		return ResponseEntity.ok(sku);
	}

	@PostMapping
	public ResponseEntity<ProductModel> createProduct(@Valid @RequestBody ProductModel product,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		ProductModel createdProduct = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductModel> updateProduct(@PathVariable Integer id,
			@Valid @RequestBody ProductModel product, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		ProductModel updatedProduct = productService.updateProduct(id, product);
		return ResponseEntity.ok(updatedProduct);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProductModel> patchProduct(@PathVariable Integer id, @Valid @RequestBody ProductModel product,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, product);
		ProductModel patchedProduct = productService.patchProduct(id, product);
		return ResponseEntity.ok(patchedProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductModel> deleteProduct(@PathVariable Integer id) {
		ProductModel deletedProduct = productService.deleteProduct(id);
		return ResponseEntity.ok(deletedProduct);
	}
}
