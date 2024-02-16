package com.asj.api.repositories.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.supplier.VatConditionModel;

public interface VatConditionRepository extends JpaRepository<VatConditionModel, Integer> {

	boolean existsById(Integer id);
	
	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Integer id);
}
