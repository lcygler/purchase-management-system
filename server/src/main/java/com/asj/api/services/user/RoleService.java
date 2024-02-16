package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.user.RoleModel;
import com.asj.api.repositories.user.RoleRepository;
import com.asj.api.repositories.user.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<RoleModel> getAllRoles() {
		return roleRepository.findAll();
	}

	public RoleModel getRoleById(Integer id) {
		return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role not found"));
	}

	@Transactional
	public RoleModel createRole(RoleModel role) {
		if (!isNameUnique(role.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		role.setCreatedAt(LocalDateTime.now());
		role.setUpdatedAt(LocalDateTime.now());
		role.setIsDeleted(false);
		
		RoleModel createdRole = roleRepository.save(role);
		entityManager.refresh(createdRole);

		return createdRole;
	}

	@Transactional
	public RoleModel updateRole(Integer id, RoleModel role) {
		if (!roleRepository.existsById(id)) {
			throw new EntityNotFoundException("Role not found");
		}

		if (!isNameUniqueAndIdNot(role.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		role.setUpdatedAt(LocalDateTime.now());

		RoleModel updatedRole = roleRepository.save(role);
		entityManager.flush();
		entityManager.refresh(updatedRole);

		return updatedRole;
	}

	@Transactional
	public RoleModel patchRole(Integer id, RoleModel role) {
		RoleModel existingRole = getRoleById(id);

		if (role.getName() != null) {
			if (!isNameUniqueAndIdNot(role.getName(), id)) {
				throw new UniqueViolationException("Name must be unique");
			}
			existingRole.setName(role.getName());
		}

		if (role.getIsDeleted() != null) {
			existingRole.setIsDeleted(role.getIsDeleted());
		}

		existingRole.setUpdatedAt(LocalDateTime.now());

		roleRepository.save(existingRole);
		entityManager.flush();
		entityManager.refresh(existingRole);

		return existingRole;
	}

	@Transactional
	public RoleModel deleteRole(Integer id) {
		RoleModel deletedRole = getRoleById(id);

		if (userRepository.countByRole(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		roleRepository.deleteById(id);

		return deletedRole;
	}

	private boolean isNameUnique(String name) {
		return !roleRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !roleRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return roleRepository.existsById(id);
	}

}
