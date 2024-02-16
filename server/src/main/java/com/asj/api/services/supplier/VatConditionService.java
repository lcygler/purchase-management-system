package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.VatConditionModel;
import com.asj.api.repositories.supplier.TaxInformationRepository;
import com.asj.api.repositories.supplier.VatConditionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class VatConditionService {

	@Autowired
	private VatConditionRepository vatConditionRepository;

	@Autowired
	private TaxInformationRepository taxInformationRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<VatConditionModel> getAllVatConditions() {
		return vatConditionRepository.findAll();
	}

	public VatConditionModel getVatConditionById(Integer id) {
		return vatConditionRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("VAT Condition not found"));
	}

	@Transactional
	public VatConditionModel createVatCondition(VatConditionModel vatCondition) {
		if (!isNameUnique(vatCondition.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		vatCondition.setCreatedAt(LocalDateTime.now());
		vatCondition.setUpdatedAt(LocalDateTime.now());
		vatCondition.setIsDeleted(false);
		
		VatConditionModel createdVatCondition = vatConditionRepository.save(vatCondition);
		entityManager.refresh(createdVatCondition);

		return createdVatCondition;
	}

	@Transactional
	public VatConditionModel updateVatCondition(Integer id, VatConditionModel vatCondition) {
		if (!vatConditionRepository.existsById(id)) {
			throw new EntityNotFoundException("VAT Condition not found");
		}

		if (!isNameUniqueAndIdNot(vatCondition.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		vatCondition.setUpdatedAt(LocalDateTime.now());

		VatConditionModel updatedVatCondition = vatConditionRepository.save(vatCondition);
		entityManager.flush();
		entityManager.refresh(updatedVatCondition);

		return updatedVatCondition;
	}

	@Transactional
	public VatConditionModel patchVatCondition(Integer id, VatConditionModel vatCondition) {
		VatConditionModel existingVatCondition = getVatConditionById(id);

		if (vatCondition.getName() != null) {
			if (!isNameUniqueAndIdNot(vatCondition.getName(), id)) {
				throw new UniqueViolationException("Name must be unique");
			}
			existingVatCondition.setName(vatCondition.getName());
		}

		if (vatCondition.getIsDeleted() != null) {
			existingVatCondition.setIsDeleted(vatCondition.getIsDeleted());
		}

		existingVatCondition.setUpdatedAt(LocalDateTime.now());

		vatConditionRepository.save(existingVatCondition);
		entityManager.flush();
		entityManager.refresh(existingVatCondition);

		return existingVatCondition;
	}

	@Transactional
	public VatConditionModel deleteVatCondition(Integer id) {
		VatConditionModel deletedVatCondition = getVatConditionById(id);

		if (taxInformationRepository.countByVatCondition(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		vatConditionRepository.deleteById(id);

		return deletedVatCondition;
	}

	private boolean isNameUnique(String name) {
		return !vatConditionRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !vatConditionRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return vatConditionRepository.existsById(id);
	}

}
