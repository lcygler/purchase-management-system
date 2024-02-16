package com.asj.api.services.product;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.product.CategoryModel;
import com.asj.api.repositories.product.CategoryRepository;
import com.asj.api.repositories.product.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<CategoryModel> getAllCategories() {
		return categoryRepository.findAll();
	}

	public CategoryModel getCategoryById(Integer id) {
		return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
	}

	@Transactional
	public CategoryModel createCategory(CategoryModel category) {
		if (!isNameUnique(category.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		category.setCreatedAt(LocalDateTime.now());
		category.setUpdatedAt(LocalDateTime.now());
		category.setIsDeleted(false);
		
		CategoryModel createdCategory = categoryRepository.save(category);
		entityManager.refresh(createdCategory);

		return createdCategory;
	}

	@Transactional
	public CategoryModel updateCategory(Integer id, CategoryModel category) {
		if (!categoryRepository.existsById(id)) {
			throw new EntityNotFoundException("Product not found");
		}
		
		if (!isNameUniqueAndIdNot(category.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		category.setUpdatedAt(LocalDateTime.now());

		CategoryModel updatedCategory = categoryRepository.save(category);
		entityManager.flush();
		entityManager.refresh(updatedCategory);

		return updatedCategory;
	}

	@Transactional
	public CategoryModel patchCategory(Integer id, CategoryModel category) {
		CategoryModel existingCategory = getCategoryById(id);

		if (category.getName() != null) {
			if (!isNameUniqueAndIdNot(category.getName(), id)) {
				throw new UniqueViolationException("Name must be unique");
			}
			existingCategory.setName(category.getName());
		}

		if (category.getIsDeleted() != null) {
			if (productRepository.countByCategory(id) > 0) {
				throw new AssociatedEntitiesException();
			}
			existingCategory.setIsDeleted(category.getIsDeleted());
		}

		existingCategory.setUpdatedAt(LocalDateTime.now());

		categoryRepository.save(existingCategory);
		entityManager.flush();
		entityManager.refresh(existingCategory);

		return existingCategory;
	}

	@Transactional
	public CategoryModel deleteCategory(Integer id) {
		CategoryModel deletedCategory = getCategoryById(id);

		if (productRepository.countByCategory(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		categoryRepository.deleteById(id);

		return deletedCategory;
	}

	private boolean isNameUnique(String name) {
		return !categoryRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !categoryRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return categoryRepository.existsById(id);
	}

}
