package com.asj.api.repositories.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.order.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

	boolean existsById(Integer id);

	boolean existsByNumber(String number);

	boolean existsByNumberAndIdNot(String number, Integer id);

	@Query(value = "SELECT MAX(id) FROM orders", nativeQuery = true)
	Integer getMaxOrderId();

	@Query(value = "SELECT COUNT(*) FROM orders o WHERE o.issue_date = :todayDate", nativeQuery = true)
	int countByIssueDate(@Param("todayDate") LocalDateTime todayDate);

	@Query(value = "SELECT COUNT(*) FROM orders o WHERE o.status_id = :id", nativeQuery = true)
	int countByStatus(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM orders o WHERE o.supplier_id = :id", nativeQuery = true)
	int countBySupplier(@Param("id") Integer id);

	@Query(value = "SELECT COUNT(*) FROM orders o WHERE o.user_id = :id", nativeQuery = true)
	int countByUser(@Param("id") Integer id);

	// H2
	@Query(value = "SELECT s.business_name, COUNT(o.supplier_id) AS \"order_count\" " +
		"FROM orders o " +
		"JOIN suppliers s ON o.supplier_id = s.id " +
		"WHERE s.is_deleted = false " +
		"GROUP BY s.business_name " +
		"ORDER BY \"order_count\" DESC, s.business_name ASC " +
		"LIMIT 5", nativeQuery = true)
	List<Object[]> findTopSuppliers2();
	
	@Query(value = "SELECT EXTRACT(YEAR FROM issue_date) AS \"year\", EXTRACT(MONTH FROM issue_date) AS \"month\", SUM(total) AS \"total\" " +
        "FROM orders " +
        "WHERE issue_date >= CURRENT_DATE - INTERVAL '1' YEAR " +
        "AND status_id <> 4 " +
        "GROUP BY EXTRACT(YEAR FROM issue_date), EXTRACT(MONTH FROM issue_date) " +
        "ORDER BY EXTRACT(YEAR FROM issue_date) ASC, EXTRACT(MONTH FROM issue_date) ASC " +
        "LIMIT  12", nativeQuery = true)
	List<Object[]> findMonthsTotal2();

	// MSSQL
	@Query(value = "SELECT TOP 5 s.business_name, COUNT(o.supplier_id) AS \"order_count\" " +
        "FROM orders o " +
        "JOIN suppliers s ON o.supplier_id = s.id " +
        "WHERE s.is_deleted = 0 " +
        "GROUP BY s.business_name " +
        "ORDER BY \"order_count\" DESC, s.business_name ASC", nativeQuery = true)
	List<Object[]> findTopSuppliers();
	
	@Query(value = "SELECT TOP 12 YEAR(issue_date) AS \"year\", MONTH(issue_date) AS \"month\", SUM(total) AS \"total\" " +
        "FROM orders " +
        "WHERE issue_date >= DATEADD(YEAR, -1, GETDATE()) " +
        "AND status_id <> 4 " +
        "GROUP BY YEAR(issue_date), MONTH(issue_date) " +
        "ORDER BY YEAR(issue_date) ASC, MONTH(issue_date) ASC", nativeQuery = true)
	List<Object[]> findMonthsTotal();

}
