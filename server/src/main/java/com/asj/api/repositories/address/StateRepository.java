package com.asj.api.repositories.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.address.StateModel;

public interface StateRepository extends JpaRepository<StateModel, Integer> {

	boolean existsById(Integer id);

	List<StateModel> findByCountryId(Integer countryId);

	@Query(value = "SELECT COUNT(*) FROM states s WHERE s.country_id = :id", nativeQuery = true)
	int countByCountry(@Param("id") Integer id);

}
