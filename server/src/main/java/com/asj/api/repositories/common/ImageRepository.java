package com.asj.api.repositories.common;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.common.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Integer> {

	boolean existsById(Integer id);
}
