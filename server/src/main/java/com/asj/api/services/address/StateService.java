package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.models.address.StateModel;
import com.asj.api.repositories.address.AddressRepository;
import com.asj.api.repositories.address.StateRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StateService {

	@Autowired
	StateRepository stateRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CountryService countryService;

	@PersistenceContext
	private EntityManager entityManager;

	public List<StateModel> getAllStates() {
		return stateRepository.findAll();
	}

	public StateModel getStateById(Integer id) {
		return stateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("State not found"));
	}

	public List<StateModel> getStatesByCountry(Integer countryId) {
		return stateRepository.findByCountryId(countryId);
	}

	@Transactional
	public StateModel createState(StateModel state) {
		validateCountry(state.getCountry().getId());

		state.setCreatedAt(LocalDateTime.now());
		state.setUpdatedAt(LocalDateTime.now());
		state.setIsDeleted(false);
		
		StateModel createdState = stateRepository.save(state);
		entityManager.refresh(createdState);

		return createdState;
	}

	@Transactional
	public StateModel updateState(Integer id, StateModel state) {
		if (!stateRepository.existsById(id)) {
			throw new EntityNotFoundException("State not found");
		}

		validateCountry(state.getCountry().getId());

		state.setUpdatedAt(LocalDateTime.now());

		StateModel updatedState = stateRepository.save(state);
		entityManager.flush();
		entityManager.refresh(updatedState);

		return updatedState;
	}

	@Transactional
	public StateModel patchState(Integer id, StateModel state) {
		StateModel existingState = getStateById(id);

		if (state.getName() != null) {
			existingState.setName(state.getName());
		}

		if (state.getCountry() != null) {
			validateCountry(state.getCountry().getId());
			existingState.setCountry(state.getCountry());
		}

		if (state.getIsDeleted() != null) {
			existingState.setIsDeleted(state.getIsDeleted());
		}

		existingState.setUpdatedAt(LocalDateTime.now());

		stateRepository.save(existingState);
		entityManager.flush();
		entityManager.refresh(existingState);

		return existingState;
	}

	@Transactional
	public StateModel deleteState(Integer id) {
		StateModel deletedState = getStateById(id);

		if (addressRepository.countByState(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		stateRepository.deleteById(id);

		return deletedState;
	}

	public boolean isIdValid(Integer id) {
		return stateRepository.existsById(id);
	}

	private void validateCountry(Integer id) {
		if (!countryService.isIdValid(id)) {
			throw new InvalidIdentifierException("Country ID is not valid");
		}
	}

}
