package com.asj.api.controllers.order;

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

import com.asj.api.models.order.StatusModel;
import com.asj.api.services.order.StatusService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/statuses")
public class StatusController {

	@Autowired
	StatusService statusService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<StatusModel>> getAllStatuses() {
		List<StatusModel> statuses = statusService.getAllStatuses();
		return ResponseEntity.ok(statuses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StatusModel> getStatusById(@PathVariable Integer id) {
		StatusModel status = statusService.getStatusById(id);
		return ResponseEntity.ok(status);
	}

	@PostMapping
	public ResponseEntity<StatusModel> createStatus(@Valid @RequestBody StatusModel status,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		StatusModel createdStatus = statusService.createStatus(status);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStatus);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StatusModel> updateStatus(@PathVariable Integer id, @Valid @RequestBody StatusModel status,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		StatusModel updatedStatus = statusService.updateStatus(id, status);
		return ResponseEntity.ok(updatedStatus);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<StatusModel> patchStatus(@PathVariable Integer id, @Valid @RequestBody StatusModel status,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, status);
		StatusModel patchedStatus = statusService.patchStatus(id, status);
		return ResponseEntity.ok(patchedStatus);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<StatusModel> deleteStatus(@PathVariable Integer id) {
		StatusModel deletedStatus = statusService.deleteStatus(id);
		return ResponseEntity.ok(deletedStatus);
	}
}