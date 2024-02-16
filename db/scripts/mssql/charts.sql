-- Top Products
SELECT 
  TOP 5 p.name, 
  SUM(od.quantity) AS "total" 
FROM 
  order_details od 
  JOIN products p ON od.product_id = p.id 
  JOIN suppliers s ON p.supplier_id = s.id 
WHERE 
  p.is_deleted = 0 
  AND s.is_deleted = 0 
GROUP BY 
  p.name 
ORDER BY 
  "total" DESC, 
  p.name ASC;
  
-- Top Suppliers
SELECT 
  TOP 5 s.business_name, 
  COUNT(o.supplier_id) AS "order_count" 
FROM 
  orders o 
  JOIN suppliers s ON o.supplier_id = s.id 
WHERE 
  s.is_deleted = 0 
GROUP BY 
  s.business_name 
ORDER BY 
  "order_count" DESC, 
  s.business_name ASC;

-- Months Total
SELECT 
  TOP 12 YEAR(issue_date) AS "year", 
  MONTH(issue_date) AS "month", 
  SUM(total) AS "total" 
FROM 
  orders 
WHERE 
  issue_date >= DATEADD(
    YEAR, 
    -1, 
    GETDATE()
  ) 
  AND status_id <> 4 
GROUP BY 
  YEAR(issue_date), 
  MONTH(issue_date) 
ORDER BY 
  YEAR(issue_date) ASC, 
  MONTH(issue_date) ASC;
