package com.asj.api.repositories.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.product.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

	boolean existsById(Integer id);

	boolean existsBySku(String sku);

	boolean existsBySkuAndIdNot(String sku, Integer id);

	List<ProductModel> findBySupplierId(Integer supplierId);

	@Query(value = "SELECT * FROM products WHERE id = :productId", nativeQuery = true)
	ProductModel getProductById(@Param("productId") Integer productId);

	@Query(value = "SELECT MAX(id) FROM products", nativeQuery = true)
	Integer getMaxProductId();

	@Query(value = "SELECT COUNT(*) FROM products p WHERE p.category_id = :id", nativeQuery = true)
	int countByCategory(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM products p WHERE p.image_id = :id", nativeQuery = true)
	int countByImage(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM products p WHERE p.supplier_id = :id", nativeQuery = true)
	int countBySupplier(@Param("id") Integer id);

}
