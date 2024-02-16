package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.IndustryModel;
import com.asj.api.repositories.supplier.IndustryRepository;
import com.asj.api.repositories.supplier.SupplierRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class IndustryService {

	@Autowired
	private IndustryRepository industryRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<IndustryModel> getAllIndustries() {
		return industryRepository.findAll();
	}

	public IndustryModel getIndustryById(Integer id) {
		return industryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Industry not found"));
	}

	@Transactional
	public IndustryModel createIndustry(IndustryModel industry) {
		if (!isNameUnique(industry.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		industry.setCreatedAt(LocalDateTime.now());
		industry.setUpdatedAt(LocalDateTime.now());
		industry.setIsDeleted(false);
		
		IndustryModel createdIndustry = industryRepository.save(industry);
		entityManager.refresh(createdIndustry);

		return createdIndustry;
	}

	@Transactional
	public IndustryModel updateIndustry(Integer id, IndustryModel industry) {
		if (!industryRepository.existsById(id)) {
			throw new EntityNotFoundException("Industry not found");
		}

		if (!isNameUniqueAndIdNot(industry.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		industry.setUpdatedAt(LocalDateTime.now());

		IndustryModel updatedIndustry = industryRepository.save(industry);
		entityManager.flush();
		entityManager.refresh(updatedIndustry);

		return updatedIndustry;
	}

	@Transactional
	public IndustryModel patchIndustry(Integer id, IndustryModel industry) {
		IndustryModel existingIndustry = getIndustryById(id);

		if (industry.getName() != null) {
			if (!isNameUniqueAndIdNot(industry.getName(), id)) {
				throw new UniqueViolationException("Name must be unique");
			}
			existingIndustry.setName(industry.getName());
		}

		if (industry.getIsDeleted() != null) {
			if (supplierRepository.countByIndustry(id) > 0) {
				throw new AssociatedEntitiesException();
			}
			existingIndustry.setIsDeleted(industry.getIsDeleted());
		}

		existingIndustry.setUpdatedAt(LocalDateTime.now());

		industryRepository.save(existingIndustry);
		entityManager.flush();
		entityManager.refresh(existingIndustry);

		return existingIndustry;
	}

	@Transactional
	public IndustryModel deleteIndustry(Integer id) {
		IndustryModel deletedIndustry = getIndustryById(id);
		
		if (supplierRepository.countByIndustry(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		industryRepository.deleteById(id);

		return deletedIndustry;
	}

	private boolean isNameUnique(String name) {
		return !industryRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !industryRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return industryRepository.existsById(id);
	}

}
