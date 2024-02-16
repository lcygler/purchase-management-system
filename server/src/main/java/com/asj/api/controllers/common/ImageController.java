package com.asj.api.controllers.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.common.ImageModel;
import com.asj.api.services.common.ImageService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	ImageService imageService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<ImageModel>> getAllImages() {
		List<ImageModel> images = imageService.getAllImages();
		return ResponseEntity.ok(images);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ImageModel> getImageById(@PathVariable Integer id) {
		ImageModel image = imageService.getImageById(id);
		return ResponseEntity.ok(image);
	}

	@PostMapping
	public ResponseEntity<ImageModel> createImage(@Valid @RequestBody ImageModel image, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		ImageModel createdImage = imageService.createImage(image);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdImage);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ImageModel> updateImage(@PathVariable Integer id, @Valid @RequestBody ImageModel image,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		ImageModel updatedImage = imageService.updateImage(id, image);
		return ResponseEntity.ok(updatedImage);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ImageModel> patchImage(@PathVariable Integer id, @Valid @RequestBody ImageModel image,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, image);
		ImageModel patchedImage = imageService.patchImage(id, image);
		return ResponseEntity.ok(patchedImage);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ImageModel> deleteImage(@PathVariable Integer id) {
		ImageModel deletedImage = imageService.deleteImage(id);
		return ResponseEntity.ok(deletedImage);
	}
}
