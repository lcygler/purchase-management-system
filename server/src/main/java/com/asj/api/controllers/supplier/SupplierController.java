package com.asj.api.controllers.supplier;

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

import com.asj.api.models.supplier.SupplierModel;
import com.asj.api.services.supplier.SupplierService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

	@Autowired
	SupplierService supplierService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<SupplierModel>> getAllSuppliers() {
		List<SupplierModel> suppliers = supplierService.getAllSuppliers();
		return ResponseEntity.ok(suppliers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SupplierModel> getSupplierById(@PathVariable Integer id) {
		SupplierModel supplier = supplierService.getSupplierById(id);
		return ResponseEntity.ok(supplier);
	}

	@GetMapping("/code")
	public ResponseEntity<String> getSupplierCode() {
		String code = supplierService.generateSupplierCode();
		return ResponseEntity.ok(code);
	}

	@PostMapping
	public ResponseEntity<SupplierModel> createSupplier(@Valid @RequestBody SupplierModel supplier,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		SupplierModel createdSupplier = supplierService.createSupplier(supplier);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SupplierModel> updateSupplier(@PathVariable Integer id,
			@Valid @RequestBody SupplierModel supplier, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		SupplierModel updatedSupplier = supplierService.updateSupplier(id, supplier);
		return ResponseEntity.ok(updatedSupplier);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<SupplierModel> patchSupplier(@PathVariable Integer id,
			@Valid @RequestBody SupplierModel supplier, BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, supplier);
		SupplierModel patchedSupplier = supplierService.patchSupplier(id, supplier);
		return ResponseEntity.ok(patchedSupplier);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SupplierModel> deleteSupplier(@PathVariable Integer id) {
		SupplierModel deletedSupplier = supplierService.deleteSupplier(id);
		return ResponseEntity.ok(deletedSupplier);
	}
}
