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

import com.asj.api.models.supplier.TaxInformationModel;
import com.asj.api.services.supplier.TaxInformationService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tax-information")
public class TaxInformationController {

	@Autowired
	TaxInformationService taxInformationService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<TaxInformationModel>> getAllTaxInformation() {
		List<TaxInformationModel> taxInformation = taxInformationService.getAllTaxInformation();
		return ResponseEntity.ok(taxInformation);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaxInformationModel> getTaxInformationById(@PathVariable Integer id) {
		TaxInformationModel taxInformation = taxInformationService.getTaxInformationById(id);
		return ResponseEntity.ok(taxInformation);
	}

	@PostMapping
	public ResponseEntity<TaxInformationModel> createTaxInformation(
			@Valid @RequestBody TaxInformationModel taxInformation, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		TaxInformationModel createdTaxInformation = taxInformationService.createTaxInformation(taxInformation);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTaxInformation);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaxInformationModel> updateTaxInformation(@PathVariable Integer id,
			@Valid @RequestBody TaxInformationModel taxInformation, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		TaxInformationModel updatedTaxInformation = taxInformationService.updateTaxInformation(id, taxInformation);
		return ResponseEntity.ok(updatedTaxInformation);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<TaxInformationModel> patchTaxInformation(@PathVariable Integer id,
			@Valid @RequestBody TaxInformationModel taxInformation, BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, taxInformation);
		TaxInformationModel patchedTaxInformation = taxInformationService.patchTaxInformation(id, taxInformation);
		return ResponseEntity.ok(patchedTaxInformation);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<TaxInformationModel> deleteTaxInformation(@PathVariable Integer id) {
		TaxInformationModel deletedTaxInformation = taxInformationService.deleteTaxInformation(id);
		return ResponseEntity.ok(deletedTaxInformation);
	}
}
