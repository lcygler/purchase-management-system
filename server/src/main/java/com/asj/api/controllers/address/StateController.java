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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.address.StateModel;
import com.asj.api.services.address.StateService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/states")
public class StateController {

	@Autowired
	StateService stateService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<StateModel>> getAllStates() {
		List<StateModel> states = stateService.getAllStates();
		return ResponseEntity.ok(states);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StateModel> getStateById(@PathVariable Integer id) {
		StateModel state = stateService.getStateById(id);
		return ResponseEntity.ok(state);
	}

	@GetMapping(params = "countryId") // [GET] /states?countryId={id}
	public ResponseEntity<List<StateModel>> getStatesByCountry(@RequestParam Integer countryId) {
		List<StateModel> states = stateService.getStatesByCountry(countryId);
		return ResponseEntity.ok(states);
	}

	@PostMapping
	public ResponseEntity<StateModel> createState(@Valid @RequestBody StateModel state, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		StateModel createdState = stateService.createState(state);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdState);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StateModel> updateState(@PathVariable Integer id, @Valid @RequestBody StateModel state,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		StateModel updatedState = stateService.updateState(id, state);
		return ResponseEntity.ok(updatedState);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<StateModel> patchState(@PathVariable Integer id, @Valid @RequestBody StateModel state,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, state);
		StateModel patchedState = stateService.patchState(id, state);
		return ResponseEntity.ok(patchedState);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<StateModel> deleteState(@PathVariable Integer id) {
		StateModel deletedState = stateService.deleteState(id);
		return ResponseEntity.ok(deletedState);
	}
}
