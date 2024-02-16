package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.models.address.AddressModel;
import com.asj.api.repositories.address.AddressRepository;
import com.asj.api.repositories.user.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	StateService stateService;

	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<AddressModel> getAllAddresses() {
		return addressRepository.findAll();
	}

	public AddressModel getAddressById(Integer id) {
		return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
	}

	@Transactional
	public AddressModel createAddress(AddressModel address) {
		validateState(address.getState().getId());

		address.setCreatedAt(LocalDateTime.now());
		address.setUpdatedAt(LocalDateTime.now());
		address.setIsDeleted(false);
		
		AddressModel createdAddress = addressRepository.save(address);
		entityManager.refresh(createdAddress);

		return createdAddress;
	}

	@Transactional
	public AddressModel updateAddress(Integer id, AddressModel address) {
		if (!addressRepository.existsById(id)) {
			throw new EntityNotFoundException("Address not found");
		}

		validateState(address.getState().getId());

		address.setUpdatedAt(LocalDateTime.now());

		AddressModel updatedAddress = addressRepository.save(address);
		entityManager.flush();
		entityManager.refresh(updatedAddress);

		return updatedAddress;
	}

	@Transactional
	public AddressModel patchAddress(Integer id, AddressModel address) {
		AddressModel existingAddress = getAddressById(id);

		if (address.getStreet() != null) {
			existingAddress.setStreet(address.getStreet());
		}

		if (address.getNumber() != null) {
			existingAddress.setNumber(address.getNumber());
		}

		if (address.getPostalCode() != null) {
			existingAddress.setPostalCode(address.getPostalCode());
		}

		if (address.getCity() != null) {
			existingAddress.setCity(address.getCity());
		}

		if (address.getState() != null) {
			validateState(address.getState().getId());
			existingAddress.setState(address.getState());
		}

		if (address.getIsDeleted() != null) {
			existingAddress.setIsDeleted(address.getIsDeleted());
		}

		existingAddress.setUpdatedAt(LocalDateTime.now());

		addressRepository.save(existingAddress);
		entityManager.flush();
		entityManager.refresh(existingAddress);

		return existingAddress;
	}

	@Transactional
	public AddressModel deleteAddress(Integer id) {
		AddressModel deletedAddress = getAddressById(id);

		if (userRepository.countByAddress(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		addressRepository.deleteById(id);

		return deletedAddress;
	}

	public boolean isIdValid(Integer id) {
		return addressRepository.existsById(id);
	}

	private void validateState(Integer id) {
		if (!stateService.isIdValid(id)) {
			throw new InvalidIdentifierException("State ID is not valid");
		}
	}

}
