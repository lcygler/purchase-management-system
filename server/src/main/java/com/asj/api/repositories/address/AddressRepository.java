package com.asj.api.repositories.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.address.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel, Integer> {

	boolean existsById(Integer id);

	@Query(value = "SELECT COUNT(*) FROM addresses a WHERE a.state_id = :id", nativeQuery = true)
	int countByState(@Param("id") Integer id);

}
