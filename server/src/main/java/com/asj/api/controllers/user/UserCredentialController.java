package com.asj.api.controllers.user;

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

import com.asj.api.models.user.UserCredentialModel;
import com.asj.api.services.user.UserCredentialService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-credentials")
public class UserCredentialController {

	@Autowired
	UserCredentialService userCredentialService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<UserCredentialModel>> getAllUserCredentials() {
		List<UserCredentialModel> userCredentials = userCredentialService.getAllUserCredentials();
		return ResponseEntity.ok(userCredentials);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserCredentialModel> getUserCredentialById(@PathVariable Integer id) {
		UserCredentialModel userCredential = userCredentialService.getUserCredentialById(id);
		return ResponseEntity.ok(userCredential);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserCredentialModel> getUserCredentialByUserId(@PathVariable Integer id) {
		UserCredentialModel userCredential = userCredentialService.getUserCredentialByUserId(id);
		return ResponseEntity.ok(userCredential);
	}

	@PostMapping
	public ResponseEntity<UserCredentialModel> createUserCredential(
			@Valid @RequestBody UserCredentialModel userCredential, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		UserCredentialModel createdUserCredential = userCredentialService.createUserCredential(userCredential);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUserCredential);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserCredentialModel> updateUserCredential(@PathVariable Integer id,
			@Valid @RequestBody UserCredentialModel userCredential, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		UserCredentialModel updatedUserCredential = userCredentialService.updateUserCredential(id, userCredential);
		return ResponseEntity.ok(updatedUserCredential);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserCredentialModel> patchUserCredential(@PathVariable Integer id,
			@Valid @RequestBody UserCredentialModel userCredential, BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, userCredential);
		UserCredentialModel patchedUserCredential = userCredentialService.patchUserCredential(id, userCredential);
		return ResponseEntity.ok(patchedUserCredential);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserCredentialModel> deleteUserCredential(@PathVariable Integer id) {
		UserCredentialModel deletedUserCredential = userCredentialService.deleteUserCredential(id);
		return ResponseEntity.ok(deletedUserCredential);
	}
}
