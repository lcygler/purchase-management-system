package com.asj.api.repositories.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.supplier.ContactDetailModel;

public interface ContactDetailRepository extends JpaRepository<ContactDetailModel, Integer> {

	boolean existsById(Integer id);
}
