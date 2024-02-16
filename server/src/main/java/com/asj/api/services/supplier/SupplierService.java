package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.InvalidIdentifierException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.supplier.SupplierModel;
import com.asj.api.repositories.order.OrderRepository;
import com.asj.api.repositories.product.ProductRepository;
import com.asj.api.repositories.supplier.SupplierRepository;
import com.asj.api.services.address.AddressService;
import com.asj.api.services.common.ImageService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private IndustryService industryService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private TaxInformationService taxInformationService;

	@Autowired
	private ContactDetailService contactDetailService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<SupplierModel> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	public SupplierModel getSupplierById(Integer id) {
		return supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
	}

	@Transactional
	public SupplierModel createSupplier(SupplierModel supplier) {
		if (!isCodeUnique(supplier.getCode())) {
			throw new UniqueViolationException("Supplier code must be unique");
		}

		validateIndustry(supplier.getIndustry().getId());
		validateAddress(supplier.getAddress().getId());
		validateTaxInformation(supplier.getTaxInformation().getId());
		validateContactDetails(supplier.getContactDetails().getId());

		if (supplier.getImage() != null) {
			validateImage(supplier.getImage().getId());
		}

		supplier.setCreatedAt(LocalDateTime.now());
		supplier.setUpdatedAt(LocalDateTime.now());
		supplier.setIsDeleted(false);

		SupplierModel createdSupplier = supplierRepository.save(supplier);
		entityManager.refresh(createdSupplier);

		return createdSupplier;
	}

	@Transactional
	public SupplierModel updateSupplier(Integer id, SupplierModel supplier) {
		if (!supplierRepository.existsById(id)) {
			throw new EntityNotFoundException("Supplier not found");
		}

		if (!isCodeUniqueAndIdNot(supplier.getCode(), id)) {
			throw new UniqueViolationException("Supplier code must be unique");
		}

		validateIndustry(supplier.getIndustry().getId());
		validateAddress(supplier.getAddress().getId());
		validateTaxInformation(supplier.getTaxInformation().getId());
		validateContactDetails(supplier.getContactDetails().getId());

		if (supplier.getImage() != null) {
			validateImage(supplier.getImage().getId());
		}

		supplier.setUpdatedAt(LocalDateTime.now());

		SupplierModel updatedSupplier = supplierRepository.save(supplier);
		entityManager.flush();
		entityManager.refresh(updatedSupplier);

		return updatedSupplier;
	}

	@Transactional
	public SupplierModel patchSupplier(Integer id, SupplierModel supplier) {
		SupplierModel existingSupplier = getSupplierById(id);

		if (supplier.getCode() != null) {
			if (!isCodeUniqueAndIdNot(supplier.getCode(), id)) {
				throw new UniqueViolationException("Supplier code must be unique");
			}
			existingSupplier.setCode(supplier.getCode());
		}

		if (supplier.getBusinessName() != null) {
			existingSupplier.setBusinessName(supplier.getBusinessName());
		}

		if (supplier.getIndustry() != null) {
			validateIndustry(supplier.getIndustry().getId());
			existingSupplier.setIndustry(supplier.getIndustry());
		}

		if (supplier.getWebsite() != null) {
			existingSupplier.setWebsite(supplier.getWebsite());
		}

		if (supplier.getEmail() != null) {
			existingSupplier.setEmail(supplier.getEmail());
		}

		if (supplier.getPhone() != null) {
			existingSupplier.setPhone(supplier.getPhone());
		}

		if (supplier.getImage() != null) {
			existingSupplier.setImage(supplier.getImage());
		}

		if (supplier.getAddress() != null) {
			validateAddress(supplier.getAddress().getId());
			existingSupplier.setAddress(supplier.getAddress());
		}

		if (supplier.getTaxInformation() != null) {
			validateTaxInformation(supplier.getTaxInformation().getId());
			existingSupplier.setTaxInformation(supplier.getTaxInformation());
		}

		if (supplier.getContactDetails() != null) {
			validateContactDetails(supplier.getContactDetails().getId());
			existingSupplier.setContactDetails(supplier.getContactDetails());
		}

		if (supplier.getIsDeleted() != null) {
			existingSupplier.setIsDeleted(supplier.getIsDeleted());
		}

		existingSupplier.setUpdatedAt(LocalDateTime.now());

		supplierRepository.save(existingSupplier);
		entityManager.flush();
		entityManager.refresh(existingSupplier);

		return existingSupplier;
	}

	@Transactional
	public SupplierModel deleteSupplier(Integer id) {
		SupplierModel deletedSupplier = getSupplierById(id);

		if (productRepository.countBySupplier(id) > 0 || orderRepository.countBySupplier(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		supplierRepository.deleteById(id);

		return deletedSupplier;
	}

	public String generateSupplierCode() {
		int length = 8;
		Random random = new Random();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		while (true) {
			StringBuilder sb = new StringBuilder(length);

			for (int i = 0; i < length; i++) {
				int index = random.nextInt(characters.length());
				sb.append(characters.charAt(index));
			}

			String generatedCode = sb.toString();

			if (isCodeUnique(generatedCode)) {
				return generatedCode;
			}
		}
	}

	private boolean isCodeUnique(String code) {
		return !supplierRepository.existsByCode(code);
	}

	private boolean isCodeUniqueAndIdNot(String code, Integer id) {
		return !supplierRepository.existsByCodeAndIdNot(code, id);
	}

	public boolean isIdValid(Integer id) {
		return supplierRepository.existsById(id);
	}

	private void validateIndustry(Integer id) {
		if (!industryService.isIdValid(id)) {
			throw new InvalidIdentifierException("Industry ID is not valid");
		}
	}

	private void validateImage(Integer id) {
		if (!imageService.isIdValid(id)) {
			throw new InvalidIdentifierException("Image ID is not valid");
		}
	}

	private void validateAddress(Integer id) {
		if (!addressService.isIdValid(id)) {
			throw new InvalidIdentifierException("Address ID is not valid");
		}
	}

	private void validateTaxInformation(Integer id) {
		if (!taxInformationService.isIdValid(id)) {
			throw new InvalidIdentifierException("Tax information ID is not valid");
		}
	}

	private void validateContactDetails(Integer id) {
		if (!contactDetailService.isIdValid(id)) {
			throw new InvalidIdentifierException("Contact detail ID is not valid");
		}
	}

}