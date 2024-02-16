package com.asj.api.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.user.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Integer> {

	boolean existsById(Integer id);
	
	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Integer id);
	
}
