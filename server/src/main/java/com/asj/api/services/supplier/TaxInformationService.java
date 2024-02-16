package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.TaxInformationModel;
import com.asj.api.repositories.supplier.SupplierRepository;
import com.asj.api.repositories.supplier.TaxInformationRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TaxInformationService {

	@Autowired
	private TaxInformationRepository taxInformationRepository;

	@Autowired
	private VatConditionService vatConditionService;

	@Autowired
	private SupplierRepository supplierRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<TaxInformationModel> getAllTaxInformation() {
		return taxInformationRepository.findAll();
	}

	public TaxInformationModel getTaxInformationById(Integer id) {
		return taxInformationRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Tax information not found"));
	}

	@Transactional
	public TaxInformationModel createTaxInformation(TaxInformationModel taxInformation) {
		if (!isCuitUnique(taxInformation.getCuit())) {
			throw new UniqueViolationException("CUIT must be unique");
		}

		validateVatCondition(taxInformation.getVatCondition().getId());

		taxInformation.setCreatedAt(LocalDateTime.now());
		taxInformation.setUpdatedAt(LocalDateTime.now());
		taxInformation.setIsDeleted(false);

		TaxInformationModel createdTaxInformation = taxInformationRepository.save(taxInformation);
		entityManager.refresh(createdTaxInformation);

		return createdTaxInformation;
	}

	@Transactional
	public TaxInformationModel updateTaxInformation(Integer id, TaxInformationModel taxInformation) {
		if (!taxInformationRepository.existsById(id)) {
			throw new EntityNotFoundException("Tax information not found");
		}

		if (!isCuitUniqueAndIdNot(taxInformation.getCuit(), id)) {
			throw new UniqueViolationException("CUIT must be unique");
		}

		validateVatCondition(taxInformation.getVatCondition().getId());

		taxInformation.setUpdatedAt(LocalDateTime.now());

		TaxInformationModel updatedTaxInformation = taxInformationRepository.save(taxInformation);
		entityManager.flush();
		entityManager.refresh(updatedTaxInformation);

		return updatedTaxInformation;
	}

	@Transactional
	public TaxInformationModel patchTaxInformation(Integer id, TaxInformationModel taxInformation) {
		TaxInformationModel existingTaxInformation = getTaxInformationById(id);

		if (taxInformation.getCuit() != null) {
			if (!isCuitUniqueAndIdNot(taxInformation.getCuit(), id)) {
				throw new UniqueViolationException("CUIT must be unique");
			}
			existingTaxInformation.setCuit(taxInformation.getCuit());
		}

		if (taxInformation.getVatCondition() != null) {
			validateVatCondition(taxInformation.getVatCondition().getId());
			existingTaxInformation.setVatCondition(taxInformation.getVatCondition());
		}

		if (taxInformation.getIsDeleted() != null) {
			existingTaxInformation.setIsDeleted(taxInformation.getIsDeleted());
		}

		existingTaxInformation.setUpdatedAt(LocalDateTime.now());

		taxInformationRepository.save(existingTaxInformation);
		entityManager.flush();
		entityManager.refresh(existingTaxInformation);

		return existingTaxInformation;
	}

	@Transactional
	public TaxInformationModel deleteTaxInformation(Integer id) {
		TaxInformationModel deletedTaxInformation = getTaxInformationById(id);

		if (supplierRepository.countByTaxInformation(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		taxInformationRepository.deleteById(id);

		return deletedTaxInformation;
	}

	private boolean isCuitUnique(String cuit) {
		return !taxInformationRepository.existsByCuit(cuit);
	}

	private boolean isCuitUniqueAndIdNot(String cuit, Integer id) {
		return !taxInformationRepository.existsByCuitAndIdNot(cuit, id);
	}

	public boolean isIdValid(Integer id) {
		return taxInformationRepository.existsById(id);
	}

	private void validateVatCondition(Integer id) {
		if (!vatConditionService.isIdValid(id)) {
			throw new InvalidIdentifierException("Vat condition ID is not valid");
		}
	}

}
