package com.asj.api.services.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.order.StatusModel;
import com.asj.api.repositories.order.OrderRepository;
import com.asj.api.repositories.order.StatusRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private OrderRepository orderRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<StatusModel> getAllStatuses() {
		return statusRepository.findAll();
	}

	public StatusModel getStatusById(Integer id) {
		return statusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Status not found"));
	}

	@Transactional
	public StatusModel createStatus(StatusModel status) {
		if (!isNameUnique(status.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		status.setCreatedAt(LocalDateTime.now());
		status.setUpdatedAt(LocalDateTime.now());
		status.setIsDeleted(false);
		
		StatusModel createdStatus = statusRepository.save(status);
		entityManager.refresh(createdStatus);

		return createdStatus;
	}

	@Transactional
	public StatusModel updateStatus(Integer id, StatusModel status) {
		if (!statusRepository.existsById(id)) {
			throw new EntityNotFoundException("Status not found");
		}

		if (!isNameUniqueAndIdNot(status.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		status.setUpdatedAt(LocalDateTime.now());

		StatusModel updatedStatus = statusRepository.save(status);
		entityManager.flush();
		entityManager.refresh(updatedStatus);

		return updatedStatus;
	}

	@Transactional
	public StatusModel patchStatus(Integer id, StatusModel status) {
		StatusModel existingStatus = getStatusById(id);

		if (status.getName() != null) {
			if (!isNameUniqueAndIdNot(status.getName(), id)) {
				throw new UniqueViolationException("Name must be unique");
			}
			existingStatus.setName(status.getName());
		}

		if (status.getIsDeleted() != null) {
			existingStatus.setIsDeleted(status.getIsDeleted());
		}

		existingStatus.setUpdatedAt(LocalDateTime.now());

		statusRepository.save(existingStatus);
		entityManager.flush();
		entityManager.refresh(existingStatus);

		return existingStatus;
	}

	@Transactional
	public StatusModel deleteStatus(Integer id) {
		StatusModel deletedStatus = getStatusById(id);

		if (orderRepository.countByStatus(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		statusRepository.deleteById(id);

		return deletedStatus;
	}

	private boolean isNameUnique(String name) {
		return !statusRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !statusRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return statusRepository.existsById(id);
	}

}
