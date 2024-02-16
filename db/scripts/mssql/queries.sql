-- 1. Obtener todos los productos, mostrando nombre del producto, categoría,
-- proveedor (razón social y codigo proveedor), precio.
SELECT 
  p.name AS 'Nombre', 
  c.name AS 'Categoría', 
  s.business_name AS 'Proveedor', 
  s.code AS 'Cód. Proveedor', 
  p.price AS 'Precio' 
FROM 
  products p 
  JOIN categories c ON p.category_id = c.id 
  JOIN suppliers s ON p.supplier_id = s.id;

-- 2. En el listado anterior, además de los datos mostrados, traer el campo imagen
-- aunque el producto NO tenga una. Sino tiene imagen, mostrar "-".
SELECT 
  p.name AS 'Nombre', 
  c.name AS 'Categoría', 
  s.business_name AS 'Proveedor', 
  s.code AS 'Cód. Proveedor', 
  p.price AS 'Precio', 
  ISNULL(i.url, '-') AS 'Imagen' 
FROM 
  products p 
  JOIN categories c ON p.category_id = c.id 
  JOIN suppliers s ON p.supplier_id = s.id 
  LEFT JOIN images i ON p.image_id = i.id;

-- 3. Mostrar los datos que se pueden modificar (en el front) del producto con ID = 2.
SELECT 
  p.sku AS 'SKU', 
  p.name AS 'Nombre', 
  p.description AS 'Descripción', 
  ISNULL(i.url, '-') AS 'Imagen', 
  c.name AS 'Categoría', 
  s.business_name AS 'Proveedor', 
  p.price AS 'Precio', 
  CASE WHEN p.is_deleted = 1 THEN 'Sí' ELSE 'No' END AS 'Eliminado' 
FROM 
  products p 
  JOIN categories c ON p.category_id = c.id 
  JOIN suppliers s ON p.supplier_id = s.id 
  LEFT JOIN images i ON p.image_id = i.id 
WHERE 
  p.id = 2;

-- 4. Listar todos los proveedores cuyo teléfono tenga la característica de Córdoba
-- o que la provincia sea igual a alguna de las 3 con más proveedores.
WITH TopStates AS (
  SELECT 
    TOP 3 st.id AS 'state_id', 
    COUNT(*) AS 'cantidad' 
  FROM 
    suppliers s 
    JOIN addresses a ON s.address_id = a.id 
    JOIN states st ON a.state_id = st.id 
  GROUP BY 
    st.id 
  ORDER BY 
    'cantidad' DESC
) 

SELECT 
  s.code AS 'Código', 
  s.business_name AS 'Razón Social', 
  s.phone AS 'Teléfono', 
  a.city AS 'Ciudad', 
  st.name AS 'Provincia' 
FROM 
  suppliers s 
  JOIN addresses a ON s.address_id = a.id 
  JOIN states st ON a.state_id = st.id 
WHERE 
  s.phone LIKE '+54 351%' 
  OR st.id IN (
    SELECT 
      state_id 
    FROM 
      TopStates
  );

-- 5. Traer un listado de todos los proveedores que no hayan sido eliminados,
-- y ordenados por razon social, codigo proveedor y fecha en que se dió de alta ASC.
-- De este listado mostrar los datos que correspondan con su tabla del front.
SELECT 
  s.code AS 'Código', 
  s.business_name AS 'Razón Social', 
  CONCAT(cd.first_name, ' ', cd.last_name) AS 'Contacto', 
  s.email AS 'Email', 
  s.phone AS 'Teléfono', 
  s.website AS 'Sitio Web' 
FROM 
  suppliers s 
  JOIN addresses a ON s.address_id = a.id 
  JOIN states st ON a.state_id = st.id 
  JOIN contact_details cd ON s.contact_detail_id = cd.id 
WHERE 
  s.is_deleted = 0 
ORDER BY 
  s.business_name ASC, 
  s.code ASC, 
  s.created_at ASC;

-- 6. Obtener razon social, codigo proveedor, imagen, web, email, teléfono y
-- los datos del contacto del proveedor con más ordenes de compra cargadas.
WITH TopSupplier AS (
  SELECT 
    TOP 1 s.id AS 'supplier_id' 
  FROM 
    suppliers s 
    JOIN orders o ON s.id = o.supplier_id 
  GROUP BY 
    s.id 
  ORDER BY 
    COUNT(o.id) DESC
)

SELECT 
  s.code AS 'Código', 
  s.business_name AS 'Razón Social', 
  i.url AS 'Imagen', 
  s.website AS 'Sitio Web', 
  s.email AS 'Email', 
  s.phone AS 'Teléfono', 
  cd.first_name AS 'Nombre del Contacto', 
  cd.last_name AS 'Apellido del Contacto', 
  cd.phone AS 'Teléfono del Contacto', 
  cd.email AS 'Email del Contacto', 
  cd.role AS 'Rol del Contacto' 
FROM 
  suppliers s 
  JOIN addresses a ON s.address_id = a.id 
  JOIN states st ON a.state_id = st.id 
  JOIN contact_details cd ON s.contact_detail_id = cd.id 
  JOIN images i ON s.image_id = i.id 
  JOIN TopSupplier ts ON s.id = ts.supplier_id 
WHERE 
  s.is_deleted = 0; 

-- 7. Mostrar la fecha emisión, nº de orden, razon social y codigo de proveedor,
-- y la cantidad de productos de la orden.
SELECT 
  o.number AS 'Nº de Orden', 
  o.issue_date AS 'Fecha de Emisión', 
  s.code AS 'Cód. Proveedor', 
  s.business_name AS 'Razón Social', 
  SUM(od.quantity) AS 'Cant. Productos' 
FROM 
  orders o 
  JOIN suppliers s ON o.supplier_id = s.id 
  JOIN order_details od ON o.id = od.order_id 
WHERE 
  s.is_deleted = 0 
GROUP BY 
  o.number, 
  o.issue_date, 
  s.business_name, 
  s.code 
ORDER BY 
  o.number ASC;

-- 8. En el listado anterior, diferenciar cuando una orden está Cancelada o no,
-- y el total de la misma.
SELECT 
  o.number AS 'Nº de Orden', 
  o.issue_date AS 'Fecha de Emisión', 
  s.code AS 'Cód. Proveedor', 
  s.business_name AS 'Razón Social', 
  SUM(od.quantity) AS 'Cant. Productos', 
  SUM(od.price * od.quantity) AS 'Total', 
  st.name AS 'Estado' 
FROM 
  orders o 
  JOIN statuses st ON o.status_id = st.id 
  JOIN suppliers s ON o.supplier_id = s.id 
  JOIN order_details od ON o.id = od.order_id 
WHERE 
  s.is_deleted = 0 
GROUP BY 
  o.number, 
  o.issue_date, 
  s.business_name, 
  s.code, 
  st.name 
ORDER BY 
  o.number ASC;

-- 9. Mostrar el detalle de una orden de compra del proveedor 3, trayendo:
-- SKU del producto, nombre producto, cantidad y subtotal.
SELECT 
  p.sku AS 'SKU', 
  p.name AS 'Nombre', 
  od.quantity AS 'Cantidad', 
  SUM(od.price * od.quantity) AS 'Subtotal' 
FROM 
  orders o 
  JOIN order_details od ON o.id = od.order_id 
  JOIN products p ON od.product_id = p.id 
  JOIN suppliers s ON o.supplier_id = s.id 
WHERE 
  s.id = 3 
  AND o.id = 3 
GROUP BY 
  p.sku, 
  p.name, 
  od.quantity;

-- 10. Cambiar el estado a Cancelada y la fecha de modificación a la orden de compra con ID = 1.
UPDATE 
  orders 
SET 
  status_id = (
    SELECT 
      id 
    FROM 
      statuses 
    WHERE 
      name = 'Cancelado'
  ), 
  updated_at = GETDATE() 
WHERE 
  id = 1;

-- 11. Escribir la sentencia para eliminar el producto con id = 1 (NO EJECUTAR, SÓLO MOSTRAR SENTENCIA)
DELETE FROM 
  products 
WHERE 
  id = 1;

-- Borrado lógico
UPDATE 
  products 
SET 
  is_deleted = 1 
WHERE 
  id = 1;

-- 12. Mostrar los países que no tienen provincias
SELECT 
  c.id, 
  c.name 
FROM 
  countries c 
  LEFT JOIN states s ON c.id = s.country_id 
WHERE 
  s.id IS NULL 
ORDER BY 
  c.name ASC;
