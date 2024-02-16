package com.asj.api.repositories.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.supplier.TaxInformationModel;

public interface TaxInformationRepository extends JpaRepository<TaxInformationModel, Integer> {

	boolean existsById(Integer id);
	
	boolean existsByCuit(String cuit);

	boolean existsByCuitAndIdNot(String cuit, Integer id);
	
	@Query(value = "SELECT COUNT(*) FROM tax_information t WHERE t.vat_condition_id = :id", nativeQuery = true)
	int countByVatCondition(@Param("id") Integer id);
}
