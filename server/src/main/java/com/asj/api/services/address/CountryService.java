package com.asj.api.services.address;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.address.CountryModel;
import com.asj.api.repositories.address.CountryRepository;
import com.asj.api.repositories.address.StateRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<CountryModel> getAllCountries() {
		return countryRepository.findAll();
	}

	public CountryModel getCountryById(Integer id) {
		return countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country not found"));
	}

	@Transactional
	public CountryModel createCountry(CountryModel country) {
		if (!isNameUnique(country.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		country.setCreatedAt(LocalDateTime.now());
		country.setUpdatedAt(LocalDateTime.now());
		country.setIsDeleted(false);
		
		CountryModel createdCountry = countryRepository.save(country);
		entityManager.refresh(createdCountry);

		return createdCountry;
	}

	@Transactional
	public CountryModel updateCountry(Integer id, CountryModel country) {
		if (!countryRepository.existsById(id)) {
			throw new EntityNotFoundException("Country not found");
		}

		if (!isNameUniqueAndIdNot(country.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		country.setUpdatedAt(LocalDateTime.now());

		CountryModel updatedCountry = countryRepository.save(country);
		entityManager.flush();
		entityManager.refresh(updatedCountry);

		return updatedCountry;
	}

	@Transactional
	public CountryModel patchCountry(Integer id, CountryModel country) {
		CountryModel existingCountry = getCountryById(id);

		if (country.getName() != null) {
			if (!isNameUniqueAndIdNot(country.getName(), id)) {
				throw new UniqueViolationException("Name must be unique");
			}
			existingCountry.setName(country.getName());
		}

		if (country.getIsDeleted() != null) {
			existingCountry.setIsDeleted(country.getIsDeleted());
		}

		existingCountry.setUpdatedAt(LocalDateTime.now());

		countryRepository.save(existingCountry);
		entityManager.flush();
		entityManager.refresh(existingCountry);

		return existingCountry;
	}

	@Transactional
	public CountryModel deleteCountry(Integer id) {
		CountryModel deletedCountry = getCountryById(id);

		if (stateRepository.countByCountry(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		countryRepository.deleteById(id);

		return deletedCountry;
	}

	private boolean isNameUnique(String name) {
		return !countryRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !countryRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return countryRepository.existsById(id);
	}

}
