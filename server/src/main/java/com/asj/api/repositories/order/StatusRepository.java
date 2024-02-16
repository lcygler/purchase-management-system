package com.asj.api.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.order.StatusModel;

public interface StatusRepository extends JpaRepository<StatusModel, Integer> {

	boolean existsById(Integer id);
	
	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Integer id);
}
