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

import com.asj.api.models.address.AddressModel;
import com.asj.api.services.address.AddressService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	AddressService addressService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<AddressModel>> getAllAddresses() {
		List<AddressModel> addresses = addressService.getAllAddresses();
		return ResponseEntity.ok(addresses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressModel> getAddressById(@PathVariable Integer id) {
		AddressModel address = addressService.getAddressById(id);
		return ResponseEntity.ok(address);
	}

	@PostMapping
	public ResponseEntity<AddressModel> createAddress(@Valid @RequestBody AddressModel address,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		AddressModel createdAddress = addressService.createAddress(address);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressModel> updateAddress(@PathVariable Integer id,
			@Valid @RequestBody AddressModel address, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		AddressModel updatedAddress = addressService.updateAddress(id, address);
		return ResponseEntity.ok(updatedAddress);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Object> patchAddress(@PathVariable Integer id, @Valid @RequestBody AddressModel address,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, address);
		AddressModel patchedAddress = addressService.patchAddress(id, address);
		return ResponseEntity.ok(patchedAddress);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AddressModel> deleteAddress(@PathVariable Integer id) {
		AddressModel deletedAddress = addressService.deleteAddress(id);
		return ResponseEntity.ok(deletedAddress);
	}
}