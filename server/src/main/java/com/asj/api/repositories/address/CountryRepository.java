package com.asj.api.repositories.address;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asj.api.models.address.CountryModel;

public interface CountryRepository extends JpaRepository<CountryModel, Integer> {

	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Integer id);
}
