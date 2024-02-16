-- Top Products
SELECT 
  p.name, 
  SUM(od.quantity) AS "total" 
FROM 
  order_details od 
  JOIN products p ON od.product_id = p.id 
  JOIN suppliers s ON p.supplier_id = s.id 
WHERE 
  p.is_deleted = false 
  AND s.is_deleted = false 
GROUP BY 
  p.name 
ORDER BY 
  "total" DESC, 
  p.name ASC 
LIMIT 
  5;

-- Top Suppliers
SELECT 
  s.business_name, 
  COUNT(o.supplier_id) AS "order_count" 
FROM 
  orders o 
  JOIN suppliers s ON o.supplier_id = s.id 
WHERE 
  s.is_deleted = false 
GROUP BY 
  s.business_name 
ORDER BY 
  "order_count" DESC, 
  s.business_name ASC 
LIMIT 
  5;

-- Months Total
SELECT 
  EXTRACT(YEAR FROM issue_date) AS "year", 
  EXTRACT(MONTH  FROM issue_date) AS "month", 
  SUM(total) AS "total" 
FROM 
  orders 
WHERE 
  issue_date >= CURRENT_DATE - INTERVAL '1' YEAR 
  AND status_id <> 4 
GROUP BY 
  EXTRACT(YEAR FROM issue_date),
  EXTRACT(MONTH FROM issue_date) 
ORDER BY 
  EXTRACT(YEAR FROM issue_date) ASC, 
  EXTRACT(MONTH FROM issue_date) ASC 
LIMIT 
  12;
