package com.asj.api.controllers.address;

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

import com.asj.api.models.address.CountryModel;
import com.asj.api.services.address.CountryService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<CountryModel>> getAllCountries() {
		List<CountryModel> countries = countryService.getAllCountries();
		return ResponseEntity.ok(countries);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CountryModel> getCountryById(@PathVariable Integer id) {
		CountryModel country = countryService.getCountryById(id);
		return ResponseEntity.ok(country);
	}

	@PostMapping
	public ResponseEntity<CountryModel> createCountry(@Valid @RequestBody CountryModel country,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		CountryModel createdCountry = countryService.createCountry(country);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CountryModel> updateCountry(@PathVariable Integer id,
			@Valid @RequestBody CountryModel country, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		CountryModel updatedCountry = countryService.updateCountry(id, country);
		return ResponseEntity.ok(updatedCountry);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CountryModel> patchCountry(@PathVariable Integer id, @Valid @RequestBody CountryModel country,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, country);
		CountryModel patchedCountry = countryService.patchCountry(id, country);
		return ResponseEntity.ok(patchedCountry);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CountryModel> deleteCountry(@PathVariable Integer id) {
		CountryModel deletedCountry = countryService.deleteCountry(id);
		return ResponseEntity.ok(deletedCountry);
	}
}
