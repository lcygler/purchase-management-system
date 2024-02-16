package com.asj.api.repositories.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.supplier.SupplierModel;

public interface SupplierRepository extends JpaRepository<SupplierModel, Integer> {

	boolean existsById(Integer id);

	boolean existsByCode(String code);

	boolean existsByCodeAndIdNot(String code, Integer id);

	@Query(value = "SELECT MAX(id) FROM suppliers", nativeQuery = true)
	Integer getMaxSupplierId();

	@Query(value = "SELECT COUNT(*) FROM suppliers s WHERE s.industry_id = :id", nativeQuery = true)
	int countByIndustry(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM suppliers s WHERE s.tax_information_id = :id", nativeQuery = true)
	int countByTaxInformation(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM suppliers s WHERE s.contact_detail_id = :id", nativeQuery = true)
	int countByContactDetail(@Param("id") Integer id);
	
	@Query(value = "SELECT COUNT(*) FROM suppliers s WHERE s.image_id = :id", nativeQuery = true)
	int countByImage(@Param("id") Integer id);
}
