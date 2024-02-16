package com.asj.api.services.supplier;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.models.supplier.ContactDetailModel;
import com.asj.api.repositories.supplier.ContactDetailRepository;
import com.asj.api.repositories.supplier.SupplierRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ContactDetailService {

	@Autowired
	private ContactDetailRepository contactDetailRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<ContactDetailModel> getAllContactDetails() {
		return contactDetailRepository.findAll();
	}

	public ContactDetailModel getContactDetailById(Integer id) {
		return contactDetailRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Contact detail not found"));
	}

	@Transactional
	public ContactDetailModel createContactDetail(ContactDetailModel contactDetail) {
		contactDetail.setCreatedAt(LocalDateTime.now());
		contactDetail.setUpdatedAt(LocalDateTime.now());
		contactDetail.setIsDeleted(false);
		
		ContactDetailModel createdContactDetail = contactDetailRepository.save(contactDetail);
		entityManager.refresh(createdContactDetail);

		return createdContactDetail;
	}

	@Transactional
	public ContactDetailModel updateContactDetail(Integer id, ContactDetailModel contactDetail) {
		if (!contactDetailRepository.existsById(id)) {
			throw new EntityNotFoundException("Contact detail not found");
		}

		contactDetail.setUpdatedAt(LocalDateTime.now());

		ContactDetailModel updatedContactDetail = contactDetailRepository.save(contactDetail);
		entityManager.flush();
		entityManager.refresh(updatedContactDetail);

		return updatedContactDetail;
	}

	@Transactional
	public ContactDetailModel patchContactDetail(Integer id, ContactDetailModel contactDetail) {
		ContactDetailModel existingContactDetail = getContactDetailById(id);

		if (contactDetail.getFirstName() != null) {
			existingContactDetail.setFirstName(contactDetail.getFirstName());
		}

		if (contactDetail.getLastName() != null) {
			existingContactDetail.setLastName(contactDetail.getLastName());
		}

		if (contactDetail.getPhone() != null) {
			existingContactDetail.setPhone(contactDetail.getPhone());
		}

		if (contactDetail.getEmail() != null) {
			existingContactDetail.setEmail(contactDetail.getEmail());
		}

		if (contactDetail.getRole() != null) {
			existingContactDetail.setRole(contactDetail.getRole());
		}

		if (contactDetail.getIsDeleted() != null) {
			existingContactDetail.setIsDeleted(contactDetail.getIsDeleted());
		}

		existingContactDetail.setUpdatedAt(LocalDateTime.now());

		contactDetailRepository.save(existingContactDetail);
		entityManager.flush();
		entityManager.refresh(existingContactDetail);

		return existingContactDetail;
	}

	@Transactional
	public ContactDetailModel deleteContactDetail(Integer id) {
		ContactDetailModel deletedContactDetail = getContactDetailById(id);

		if (supplierRepository.countByContactDetail(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		contactDetailRepository.deleteById(id);

		return deletedContactDetail;
	}

	public boolean isIdValid(Integer id) {
		return contactDetailRepository.existsById(id);
	}

}
