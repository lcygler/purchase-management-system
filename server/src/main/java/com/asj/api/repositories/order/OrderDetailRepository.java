package com.asj.api.repositories.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.order.OrderDetailModel;

public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Integer> {

	List<OrderDetailModel> findByOrder_Id(Integer orderId);

	@Query(value = "SELECT COUNT(*) FROM order_details od WHERE od.order_id = :id", nativeQuery = true)
	int countByOrder(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM order_details od WHERE od.product_id = :id", nativeQuery = true)
	int countByProduct(@Param("id") Integer id);
	
	// H2
	@Query(value = "SELECT p.name, SUM(od.quantity) AS \"total\" " +
		"FROM order_details od " +
		"JOIN products p ON od.product_id = p.id " +
		"JOIN suppliers s ON p.supplier_id = s.id " +
		"WHERE p.is_deleted = false " +
		"AND s.is_deleted = false " +
		"GROUP BY p.name " +
		"ORDER BY \"total\" DESC, p.name ASC " +
		"LIMIT 5", nativeQuery = true)
	List<Object[]> findTopProducts2();
	
	// MSSQL
	@Query(value = "SELECT TOP 5 p.name, SUM(od.quantity) AS \"total\" " +
        "FROM order_details od " +
        "JOIN products p ON od.product_id = p.id " +
        "JOIN suppliers s ON p.supplier_id = s.id " +
        "WHERE p.is_deleted = 0 " +
        "AND s.is_deleted = 0 " +
        "GROUP BY p.name " +
        "ORDER BY \"total\" DESC, p.name ASC", nativeQuery = true)
	List<Object[]> findTopProducts();

}
