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

import com.asj.api.models.supplier.ContactDetailModel;
import com.asj.api.services.supplier.ContactDetailService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contact-details")
public class ContactDetailController {

	@Autowired
	ContactDetailService contactDetailService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<ContactDetailModel>> getAllContactDetails() {
		List<ContactDetailModel> contactDetails = contactDetailService.getAllContactDetails();
		return ResponseEntity.ok(contactDetails);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactDetailModel> getContactDetailById(@PathVariable Integer id) {
		ContactDetailModel contactDetail = contactDetailService.getContactDetailById(id);
		return ResponseEntity.ok(contactDetail);
	}

	@PostMapping
	public ResponseEntity<ContactDetailModel> createContactDetail(@Valid @RequestBody ContactDetailModel contactDetail,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		ContactDetailModel createdContactDetail = contactDetailService.createContactDetail(contactDetail);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdContactDetail);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ContactDetailModel> updateContactDetail(@PathVariable Integer id,
			@Valid @RequestBody ContactDetailModel contactDetail, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		ContactDetailModel updatedContactDetail = contactDetailService.updateContactDetail(id, contactDetail);
		return ResponseEntity.ok(updatedContactDetail);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ContactDetailModel> patchContactDetail(@PathVariable Integer id,
			@Valid @RequestBody ContactDetailModel contactDetail, BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, contactDetail);
		ContactDetailModel patchedContactDetail = contactDetailService.patchContactDetail(id, contactDetail);
		return ResponseEntity.ok(patchedContactDetail);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ContactDetailModel> deleteContactDetail(@PathVariable Integer id) {
		ContactDetailModel deletedContactDetail = contactDetailService.deleteContactDetail(id);
		return ResponseEntity.ok(deletedContactDetail);
	}
}