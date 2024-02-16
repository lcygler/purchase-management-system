package com.asj.api.services.common;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.models.common.ImageModel;
import com.asj.api.repositories.common.ImageRepository;
import com.asj.api.repositories.product.ProductRepository;
import com.asj.api.repositories.supplier.SupplierRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private ProductRepository productRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<ImageModel> getAllImages() {
		return imageRepository.findAll();
	}

	public ImageModel getImageById(Integer id) {
		return imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Image not found"));
	}

	@Transactional
	public ImageModel createImage(ImageModel image) {
		image.setCreatedAt(LocalDateTime.now());
		image.setUpdatedAt(LocalDateTime.now());
		image.setIsDeleted(false);
		
		ImageModel createdImage = imageRepository.save(image);
		entityManager.refresh(createdImage);

		return createdImage;
	}

	@Transactional
	public ImageModel updateImage(Integer id, ImageModel image) {
		if (!imageRepository.existsById(id)) {
			throw new EntityNotFoundException("Image not found");
		}

		image.setUpdatedAt(LocalDateTime.now());

		ImageModel updatedImage = imageRepository.save(image);
		entityManager.flush();
		entityManager.refresh(updatedImage);

		return updatedImage;
	}

	@Transactional
	public ImageModel patchImage(Integer id, ImageModel image) {
		ImageModel existingImage = getImageById(id);

		if (image.getUrl() != null) {
			existingImage.setUrl(image.getUrl());
		}

		if (image.getIsDeleted() != null) {
			existingImage.setIsDeleted(image.getIsDeleted());
		}

		existingImage.setUpdatedAt(LocalDateTime.now());

		imageRepository.save(existingImage);
		entityManager.flush();
		entityManager.refresh(existingImage);

		return existingImage;
	}

	@Transactional
	public ImageModel deleteImage(Integer id) {
		ImageModel deletedImage = getImageById(id);

		if (supplierRepository.countByImage(id) > 0 || productRepository.countByImage(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		imageRepository.deleteById(id);

		return deletedImage;
	}

	public boolean isIdValid(Integer id) {
		return imageRepository.existsById(id);
	}

}
