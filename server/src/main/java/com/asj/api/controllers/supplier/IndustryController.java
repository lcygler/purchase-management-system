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

import com.asj.api.models.supplier.IndustryModel;
import com.asj.api.services.supplier.IndustryService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/industries")
public class IndustryController {

	@Autowired
	IndustryService industryService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<IndustryModel>> getAllIndustries() {
		List<IndustryModel> industries = industryService.getAllIndustries();
		return ResponseEntity.ok(industries);
	}

	@GetMapping("/{id}")
	public ResponseEntity<IndustryModel> getIndustryById(@PathVariable Integer id) {
		IndustryModel industry = industryService.getIndustryById(id);
		return ResponseEntity.ok(industry);
	}

	@PostMapping
	public ResponseEntity<IndustryModel> createIndustry(@Valid @RequestBody IndustryModel industry,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		IndustryModel createdIndustry = industryService.createIndustry(industry);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdIndustry);
	}

	@PutMapping("/{id}")
	public ResponseEntity<IndustryModel> updateIndustry(@PathVariable Integer id,
			@Valid @RequestBody IndustryModel industry, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		IndustryModel updatedIndustry = industryService.updateIndustry(id, industry);
		return ResponseEntity.ok(updatedIndustry);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<IndustryModel> patchIndustry(@PathVariable Integer id,
			@Valid @RequestBody IndustryModel industry, BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, industry);
		IndustryModel patchedIndustry = industryService.patchIndustry(id, industry);
		return ResponseEntity.ok(patchedIndustry);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<IndustryModel> deleteIndustry(@PathVariable Integer id) {
		IndustryModel deletedIndustry = industryService.deleteIndustry(id);
		return ResponseEntity.ok(deletedIndustry);
	}
}
