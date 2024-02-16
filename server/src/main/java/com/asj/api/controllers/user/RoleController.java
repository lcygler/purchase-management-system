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

import com.asj.api.models.user.RoleModel;
import com.asj.api.services.user.RoleService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<RoleModel>> getAllRoles() {
		List<RoleModel> roles = roleService.getAllRoles();
		return ResponseEntity.ok(roles);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoleModel> getRoleById(@PathVariable Integer id) {
		RoleModel role = roleService.getRoleById(id);
		return ResponseEntity.ok(role);
	}

	@PostMapping
	public ResponseEntity<RoleModel> createRole(@Valid @RequestBody RoleModel role, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		RoleModel createdRole = roleService.createRole(role);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoleModel> updateRole(@PathVariable Integer id, @Valid @RequestBody RoleModel role,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		RoleModel updatedRole = roleService.updateRole(id, role);
		return ResponseEntity.ok(updatedRole);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<RoleModel> patchRole(@PathVariable Integer id, @Valid @RequestBody RoleModel role,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, role);
		RoleModel patchedRole = roleService.patchRole(id, role);
		return ResponseEntity.ok(patchedRole);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RoleModel> deleteRole(@PathVariable Integer id) {
		RoleModel deletedRole = roleService.deleteRole(id);
		return ResponseEntity.ok(deletedRole);
	}
}