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

import com.asj.api.dto.UserDTO;
import com.asj.api.models.user.UserModel;
import com.asj.api.services.user.UserService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<UserModel>> getAllUsers() {
		List<UserModel> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getUserById(@PathVariable Integer id) {
		UserModel user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel user, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		UserModel createdUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PostMapping("/login")
	public ResponseEntity<UserModel> loginUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		userService.validateUserCredentials(userDTO.getEmail(), userDTO.getPassword());
		UserModel user = userService.getUserByEmail(userDTO.getEmail());
		return ResponseEntity.ok(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable Integer id, @Valid @RequestBody UserModel user,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		UserModel updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserModel> patchUser(@PathVariable Integer id, @Valid @RequestBody UserModel user,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, user);
		UserModel patchedUser = userService.patchUser(id, user);
		return ResponseEntity.ok(patchedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserModel> deleteUser(@PathVariable Integer id) {
		UserModel deletedUser = userService.deleteUser(id);
		return ResponseEntity.ok(deletedUser);
	}
}
