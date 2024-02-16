package com.asj.api.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.product.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

	boolean existsById(Integer id);
	
	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Integer id);
}
