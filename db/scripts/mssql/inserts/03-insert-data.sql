-- Addresses
INSERT INTO addresses (
  street, number, postal_code, city, 
  state_id, is_deleted, created_at, updated_at
) 
VALUES 
  (
    'Av. Corrientes', '456', '1001', 'CABA', 
    175, 0, GETDATE(), GETDATE()
  ), 
  (
    'Calle Independencia', '234', '5000', 
    'Córdoba', 176, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Santa Fe', '789', '3000', 'Rosario', 
    191, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. San Martín', '123', '5500', 
    'Mendoza', 183, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. 9 de Julio', '567', '4000', 'San Miguel de Tucumán', 
    194, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Belgrano', '890', '4400', 'Salta', 
    187, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Argentina', '321', '8300', 'Neuquén', 
    185, 0, GETDATE(), GETDATE()
  ), 
  (
    'Calle Rivadavia', '654', '9000', 
    'Comodoro Rivadavia', 174, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Urquiza', '987', '3100', 'Paraná', 
    178, 0, GETDATE(), GETDATE()
  ), 
  (
    'Calle Alvear', '210', '4600', 'San Salvador de Jujuy', 
    180, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Pueyrredón', '123', '1425', 
    'CABA', 175, 0, GETDATE(), GETDATE()
  ), 
  (
    'Calle San Jerónimo', '567', '5001', 
    'Córdoba', 176, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Dorrego', '890', '3001', 'Rosario', 
    191, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Juan B. Justo', '321', '5501', 
    'Mendoza', 183, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Alem', '987', '4001', 'San Miguel de Tucumán', 
    194, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Güemes', '654', '4401', 'Salta', 
    187, 0, GETDATE(), GETDATE()
  ), 
  (
    'Calle Roca', '321', '8301', 'Neuquén', 
    185, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. San Martín', '654', '9001', 
    'Comodoro Rivadavia', 174, 0, GETDATE(), GETDATE()
  ), 
  (
    'Av. Entre Ríos', '210', '3101', 
    'Paraná', 178, 0, GETDATE(), GETDATE()
  ), 
  (
    'Calle Lavalle', '789', '4601', 'San Salvador de Jujuy', 
    180, 0, GETDATE(), GETDATE()
  ),
  (
    'Calle Suipacha', '472', '1008', 'CABA', 
    175, 0, GETDATE(), GETDATE()
  );


-- Images
INSERT INTO images (url, is_deleted, created_at, updated_at) 
VALUES 
  -- Suppliers
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704897863/asj/suppliers/electrotech_i7lyp2.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898550/asj/suppliers/ideal-hogar_n3gugt.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898551/asj/suppliers/wacker-herramientas_uzlt80.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898549/asj/suppliers/electrohogar_xbhyv5.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898552/asj/suppliers/distrito-moda_huvyr4.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898550/asj/suppliers/punto-deportivo_yj17k1.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898550/asj/suppliers/tienda-beauty_t0ah6s.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898549/asj/suppliers/el-mundo-del-juguete_nrxnci.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898550/asj/suppliers/planeta-libros_tvtdao.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898551/asj/suppliers/cafe-la-bastilla_zquw3r.jpg',
    0, GETDATE(), GETDATE()
  ), 
  -- Products
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899571/asj/products/telefono_wnd3bk.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899558/asj/products/auriculares_shgb6v.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899574/asj/products/sofa_eajhir.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899578/asj/products/mesa_rvkier.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899579/asj/products/taladro_ok4hx5.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899570/asj/products/destornilladores_xnr76o.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899560/asj/products/lavarropas_fuj7vb.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899574/asj/products/secarropas_b5fcvo.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899577/asj/products/vestido_serapp.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899557/asj/products/zapatos_hfdklt.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899575/asj/products/pelota_i4zfvi.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899568/asj/products/cinta-correr_ka3vnj.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899572/asj/products/kit-maquillaje_gex3tq.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899560/asj/products/cepillo_mxivzl.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899570/asj/products/juguetes_naxrrw.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899565/asj/products/cuna_dqaqdb.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899576/asj/products/libros_ojpnep.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899582/asj/products/libro-cocina_cv9aag.png',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899580/asj/products/maquina-cafe_mjz8gp.jpg',
    0, GETDATE(), GETDATE()
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899557/asj/products/robot-aspirador_b9fw63.jpg',
    0, GETDATE(), GETDATE()
  );

-- Users
INSERT INTO genres (name, is_deleted, created_at, updated_at) 
VALUES 
  ('Masculino', 0, GETDATE(), GETDATE()), 
  ('Femenino', 0, GETDATE(), GETDATE()), 
  ('Otros', 0, GETDATE(), GETDATE());

INSERT INTO roles (name, is_deleted, created_at, updated_at) 
VALUES 
  ('admin', 0, GETDATE(), GETDATE()), 
  ('user', 0, GETDATE(), GETDATE());

INSERT INTO users (
  first_name, last_name, dni, email, 
  phone, genre_id, address_id, role_id,
  is_deleted, created_at, updated_at
) 
VALUES 
  (
    'Juan', 'Gómez', '123456789', 'juan.gomez@gmail.com', 
    '123456789', 1, 1, 1, 0, GETDATE(), GETDATE()
  ), 
  (
    'Ana', 'Martínez', '987654328', 'ana.martinez@gmail.com', 
    '987654321', 2, 2, 1, 0, GETDATE(), GETDATE()
  ), 
  (
    'Carlos', 'López', '567890122', 'claudio.lopez@outlook.com', 
    '567890123', 1, 3, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'María', 'Rodríguez', '345678901', 
    'maria.rodriguez@gmail.com', '345678901', 
    2, 4, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'Diego', 'Sánchez', '876543213', 
    'diego.sanchez@gmail.com', '876543210', 
    3, 5, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'Laura', 'Fernández', '234567894', 
    'laura.fernandez@outlook.com', 
    '234567890', 2, 6, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'Pedro', 'García', '789012345', 'pedro.garcia@hotmail.com', 
    '789012345', 1, 7, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'Sofía', 'Pérez', '456789016', 'sofia.perez@gmail.com', 
    '456789012', 2, 8, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'Pablo', 'Hernández', '123890767', 
    'pablo.hernandez@yahoo.com', '123890765', 
    1, 9, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'Marta', 'Díaz', '908712348', 'marta.diaz@gmail.com', 
    '908712345', 2, 10, 2, 0, GETDATE(), GETDATE()
  ),
  (
    'admin', 'admin', '000000000', 'admin@asjservicios.com', 
    '123456789', 1, 21, 1, 0, GETDATE(), GETDATE()
  );

INSERT INTO user_credentials (user_id, password, is_deleted, created_at, updated_at) 
VALUES 
  (
    1, '$2a$12$7GXFLr4yQ9y22ge5u8FpLOQAK2mcMvzlJFkZlCp5Vlmzvf4xrHwJy', 0, GETDATE(), GETDATE()
  ), 
  (
    2, '$2a$12$J7RU5dTKJh/fcRrXOO06UeJb7ar2dFQ1E0KXyltx9msd7fC2aXZ3W', 0, GETDATE(), GETDATE()
  ), 
  (
    3, '$2a$12$TQApwTDX3US3pf0h0D8qCuZ4uljbUuzot2Y2Z2PS8YlMpccduLw1e', 0, GETDATE(), GETDATE()
  ), 
  (
    4, '$2a$12$Fg9jBDEjAZKX4F4OY9Vq0OKOFbHjTFotSpvTb5cNAlqZbUeBJS8FS', 0, GETDATE(), GETDATE()
  ), 
  (
    5, '$2a$12$RPWeel8EJRQIXVig0e/AQOYqOD.Ln6S3rf6ZxjWlNL0pfZsZZr98q', 0, GETDATE(), GETDATE()
  ), 
  (
    6, '$2a$12$l2rRW2iaIAYzP6OXcJWt5ePnmGUMTQbE9jVq9.ZllBxUde7cGH8fy', 0, GETDATE(), GETDATE()
  ), 
  (
    7, '$2a$12$OcTZ5X7HrH6bwG.Sb5dkLe9o7W5zgBEbzzF3RwSNCOVktU02ioNjK', 0, GETDATE(), GETDATE()
  ), 
  (
    8, '$2a$12$NZcm8h9sA3sFE0yMTfNe.uGvEetziYI9TQF94yA6DeODNSRBSwt0y', 0, GETDATE(), GETDATE()
  ), 
  (
    9, '$2a$12$.XmJb.ZlUeZr1ptvl.wn2.Ka8DTwqGZLYYzvMjpv1LbemJDCg6D/i', 0, GETDATE(), GETDATE()
  ), 
  (
    10, '$2a$12$KSa/4xwufTK1x9MF52vTbOqZnMjj2.QTRmouzGEojvC6YDwKo/fPK', 0, GETDATE(), GETDATE()
  ),
  (
    11, '$2a$10$7vlCB2EB/Ikyvc2Qvp3gDOCVFB9syWz11Tfj7lsRTvQ0SGTkWShcG', 0, GETDATE(), GETDATE()
  );

-- Suppliers
INSERT INTO industries (name, is_deleted, created_at, updated_at) 
VALUES 
  ('Tecnología', 0, GETDATE(), GETDATE()), 
  ('Hogar y Muebles', 0, GETDATE(), GETDATE()), 
  ('Herramientas', 0, GETDATE(), GETDATE()), 
  ('Electrodomésticos', 0, GETDATE(), GETDATE()), 
  ('Moda', 0, GETDATE(), GETDATE()), 
  ('Deportes y Fitness', 0, GETDATE(), GETDATE()), 
  ('Salud y Belleza', 0, GETDATE(), GETDATE()), 
  ('Juguetes y Bebés', 0, GETDATE(), GETDATE()), 
  ('Libros', 0, GETDATE(), GETDATE()), 
  ('Otros', 0, GETDATE(), GETDATE());

INSERT INTO vat_conditions (name, is_deleted, created_at, updated_at) 
VALUES 
  ('IVA Responsable Inscripto', 0, GETDATE(), GETDATE()), 
  ('IVA Responsable no Inscripto', 0, GETDATE(), GETDATE()), 
  ('IVA no Responsable', 0, GETDATE(), GETDATE()), 
  ('IVA Sujeto Exento', 0, GETDATE(), GETDATE()), 
  ('Consumidor Final', 0, GETDATE(), GETDATE()), 
  ('Responsable Monotributo', 0, GETDATE(), GETDATE()), 
  ('Sujeto no Categorizado', 0, GETDATE(), GETDATE()), 
  ('Proveedor del Exterior', 0, GETDATE(), GETDATE()), 
  ('Cliente del Exterior', 0, GETDATE(), GETDATE()), 
  (
    'IVA Liberado - Ley Nº 19.640', 0, GETDATE(), GETDATE()
  ), 
  (
    'IVA Responsable Inscripto - Agente de Percepción', 0, GETDATE(), GETDATE()
  ), 
  (
    'Pequeño Contribuyente Eventual', 0, GETDATE(), GETDATE()
  ), 
  ('Monotributista Social', 0, GETDATE(), GETDATE()), 
  (
    'Pequeño Contribuyente Eventual Social', 0, GETDATE(), GETDATE()
  );

INSERT INTO tax_information (cuit, vat_condition_id, is_deleted, created_at, updated_at) 
VALUES 
  ('45-67890123-4', 1, 0, GETDATE(), GETDATE()), 
  ('56-78901234-5', 2, 0, GETDATE(), GETDATE()), 
  ('67-89012345-6', 1, 0, GETDATE(), GETDATE()), 
  ('78-90123456-7', 1, 0, GETDATE(), GETDATE()), 
  ('89-01234567-8', 2, 0, GETDATE(), GETDATE()), 
  ('90-12345678-9', 2, 0, GETDATE(), GETDATE()), 
  ('01-23456789-0', 6, 0, GETDATE(), GETDATE()), 
  ('12-34567890-1', 6, 0, GETDATE(), GETDATE()), 
  ('23-45678901-2', 1, 0, GETDATE(), GETDATE()), 
  ('34-56789012-3', 2, 0, GETDATE(), GETDATE());

INSERT INTO contact_details (
  first_name, last_name, phone, email, 
  role, is_deleted, created_at, updated_at
) 
VALUES 
  (
    'Luis', 'Fernández', '+54 234567890', 
    'luis.fernandez@gmail.com', 'Gerente', 0, GETDATE(), GETDATE()
  ), 
  (
    'María', 'Martínez', '+54 456789012', 
    'maria.martinez@gmail.com', 'Supervisor', 0, GETDATE(), GETDATE()
  ), 
  (
    'Carlos', 'Gómez', '+54 567890123', 'carlos.gomez@hotmail.com', 
    'Empleado', 0, GETDATE(), GETDATE()
  ), 
  (
    'Laura', 'Rodríguez', '+54 678901234', 
    'laura.rodriguez@yahoo.com', 'Empleado', 0, GETDATE(), GETDATE()
  ), 
  (
    'Diego', 'Sánchez', '+54 789012345', 
    'diego.sanchez@gmail.com', 'Empleado', 0, GETDATE(), GETDATE()
  ), 
  (
    'Ana', 'Fernández', '+54 890123456', 
    'ana.fernandez@outlook.com', 'Gerente', 0, GETDATE(), GETDATE()
  ), 
  (
    'Sofía', 'García', '+54 901234567', 
    'sofia.garcia@gmail.com', 'Asistente', 0, GETDATE(), GETDATE()
  ), 
  (
    'Pablo', 'Pérez', '+54 012345678', 'pablo.perez@gmail.com', 
    'Supervisor', 0, GETDATE(), GETDATE()
  ), 
  (
    'Marta', 'Hernández', '+54 123456789', 
    'marta.hernandez@gmail.com', 'Empleado', 0, GETDATE(), GETDATE()
  ), 
  (
    'Lucas', 'Díaz', '+54 234567890', 'lucas.diaz@gmail.com', 
    'Empleado', 0, GETDATE(), GETDATE()
  );

INSERT INTO suppliers (
  code, business_name, industry_id, 
  website, email, phone, image_id, address_id, 
  tax_information_id, contact_detail_id, is_deleted, created_at, updated_at
) 
VALUES 
  (
    'DRvPVQ0Q', 'ElectroTech S.A.', 1, 'https://www.electrotech.com.ar', 
    'info@electrotech.com.ar', '+54 11 1234-5678', 
    1, 1, 1, 1, 0, GETDATE(), GETDATE()
  ), 
  (
    'fsr3abSE', 'Ideal Hogar S.A.', 2, 'https://www.idealhogar.com.ar', 
    'contacto@idealhogar.com.ar', '+54 351 9876-5432', 
    2, 2, 2, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'jzgBwIje', 'Wacker Herramientas S.R.L.', 
    3, 'https://www.wackerherramientas.com.ar', 
    'ventas@wacker.com.ar', '+54 341 5678-9012', 
    3, 3, 3, 3, 0, GETDATE(), GETDATE()
  ), 
  (
    'W9pAcGd7', 'ElectroHogar S.R.L.', 
    4, 'https://www.electrohogar.com.ar', 
    'info@electrohogar.com.ar', '+54 261 2345-6789', 
    4, 4, 4, 4, 0, GETDATE(), GETDATE()
  ), 
  (
    'Otn1ItRN', 'Distrito Moda S.A.', 5, 
    'https://www.distritomoda.com.ar', 
    'ventas@distritomoda.com.ar', '+54 381 8765-4321', 
    5, 5, 5, 5, 0, GETDATE(), GETDATE()
  ), 
  (
    'DeYCPFKl', 'Punto Deportivo S.A.', 
    6, 'https://www.puntodeportivo.com.ar', 
    'info@puntodeportivo.com.ar', '+54 387 5432-1098', 
    6, 6, 6, 6, 0, GETDATE(), GETDATE()
  ), 
  (
    'Z7MNUdnQ', 'Tienda Beauty S.A.', 7, 
    'https://www.tiendabeauty.com.ar', 
    'consultas@tiendabeauty.com.ar', 
    '+54 299 1234-5678', 7, 7, 7, 7, 0, GETDATE(), GETDATE()
  ), 
  (
    'zRTKfhuw', 'El Mundo del Juguete S.R.L.', 
    8, 'https://www.elmundodeljuguete.com.ar', 
    'ventas@elmundodeljuguete.com.ar', 
    '+54 280 9876-5432', 8, 8, 8, 8, 0, GETDATE(), GETDATE()
  ), 
  (
    'h321h6bW', 'Planeta Libros S.A.', 
    9, 'https://www.planetalibros.com.ar', 
    'info@planetalibros.com.ar', '+54 343 5678-9012', 
    9, 9, 9, 9, 0, GETDATE(), GETDATE()
  ), 
  (
    'ox4xNADv', 'Café La Bastilla S.A.', 
    10, 'https://www.tiendacafelabastilla.com.ar', 
    'ventas@labastilla.com.ar', '+54 11 2345-6789', 
    10, 10, 10, 10, 0, GETDATE(), GETDATE()
  );

-- Products
INSERT INTO categories (name, is_deleted, created_at, updated_at) 
VALUES 
  ('Tecnología', 0, GETDATE(), GETDATE()), 
  ('Hogar y Muebles', 0, GETDATE(), GETDATE()), 
  ('Herramientas', 0, GETDATE(), GETDATE()), 
  ('Electrodomésticos', 0, GETDATE(), GETDATE()), 
  ('Moda', 0, GETDATE(), GETDATE()), 
  ('Deportes y Fitness', 0, GETDATE(), GETDATE()), 
  ('Salud y Belleza', 0, GETDATE(), GETDATE()), 
  ('Juguetes y Bebés', 0, GETDATE(), GETDATE()), 
  ('Libros', 0, GETDATE(), GETDATE()), 
  ('Otros', 0, GETDATE(), GETDATE());

INSERT INTO products (
  sku, name, description, price, image_id, 
  category_id, supplier_id, is_deleted, created_at, updated_at
) 
VALUES 
  (
    'JTF9KSY4', 'Teléfono Inteligente', 
    'Teléfono inteligente de última generación', 
    159999.99, 11, 1, 1, 0, GETDATE(), GETDATE()
  ), 
  (
    '5JVHWRZN', 'Auriculares Inalámbricos', 
    'Auriculares con cancelación de ruido', 
    18999.99, 12, 1, 1, 0, GETDATE(), GETDATE()
  ), 
  (
    'C13K4K9D', 'Sofá Moderno', 'Sofá elegante para tu hogar', 
    255999.99, 13, 2, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    '50M2TTNZ', 'Mesa de Centro', 'Mesa de centro de diseño contemporáneo', 
    112999.99, 14, 2, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'GG0Q9JJW', 'Taladro Eléctrico', 'Taladro potente para proyectos de bricolaje', 
    78999.99, 15, 3, 3, 0, GETDATE(), GETDATE()
  ), 
  (
    'SB5V2MER', 'Juego de Destornilladores', 
    'Set completo de destornilladores profesionales', 
    20999.99, 16, 3, 3, 0, GETDATE(), GETDATE()
  ), 
  (
    'WP9QTTC7', 'Lavadora de Ropa', 'Lavadora eficiente con múltiples funciones', 
    588999.99, 17, 4, 4, 0, GETDATE(), GETDATE()
  ), 
  (
    'GWRQYY0V', 'Secadora de Ropa', 'Secadora de ropa con tecnología de secado rápido', 
    808999.99, 18, 4, 4, 0, GETDATE(), GETDATE()
  ), 
  (
    '7A6D0AI6', 'Vestido de Noche Elegante', 
    'Vestido largo para ocasiones especiales', 
    59900.00, 19, 5, 5, 0, GETDATE(), GETDATE()
  ), 
  (
    '42BHXP7T', 'Zapatos de Tacón Alto', 
    'Zapatos elegantes para complementar tu look', 
    108499.99, 20, 5, 5, 0, GETDATE(), GETDATE()
  ), 
  (
    'GNFWJWX3', 'Pelota de Fútbol', 'Pelota oficial para partidos de fútbol', 
    19999.99, 21, 6, 6, 0, GETDATE(), GETDATE()
  ), 
  (
    'QMZ6UFAW', 'Cinta de Correr Plegable', 
    'Cinta de correr para entrenamiento en casa', 
    314600.00, 22, 6, 6, 0, GETDATE(), GETDATE()
  ), 
  (
    'S7O4C19V', 'Kit de Maquillaje Profesional', 
    'Colección completa de maquillaje de alta calidad', 
    36100.00, 23, 7, 7, 0, GETDATE(), GETDATE()
  ), 
  (
    '7N9W6KF1', 'Cepillo Alisador de Pelo', 
    'Cepillo que alisa el pelo de forma rápida y fácil', 
    34998.99, 24, 7, 7, 0, GETDATE(), GETDATE()
  ), 
  (
    'ZZCPK0FB', 'Juguetes Educativos para Bebés', 
    'Set de juguetes didácticos para bebés', 
    2999.99, 25, 8, 8, 0, GETDATE(), GETDATE()
  ), 
  (
    'CD13WANN', 'Cuna Portátil', 'Cuna plegable y portátil para viajes', 
    227999.99, 26, 8, 8, 0, GETDATE(), GETDATE()
  ), 
  (
    '4ZSQPOPN', 'Colección de Novelas Best Sellers', 
    'Paquete con las mejores novelas del año', 
    29999.99, 27, 9, 9, 0, GETDATE(), GETDATE()
  ), 
  (
    'G5ZRMO81', 'Libro de Cocina Gourmet', 
    'Libro con recetas gourmet y técnicas culinarias', 
    21870.00, 28, 9, 9, 0, GETDATE(), GETDATE()
  ), 
  (
    'R5BKCEH1', 'Máquina de Café Automática', 
    'Máquina de café con múltiples opciones de preparación', 
    196549.99, 29, 10, 10, 0, GETDATE(), GETDATE()
  ), 
  (
    'XSTY3T6E', 'Robot Aspirador Inteligente', 
    'Aspiradora robot con navegación inteligente', 
    314999.99, 30, 10, 10, 0, GETDATE(), GETDATE()
  );

-- Orders
INSERT INTO statuses (name, is_deleted, created_at, updated_at) 
VALUES 
  ('En Proceso', 0, GETDATE(), GETDATE()), 
  ('En Camino', 0, GETDATE(), GETDATE()), 
  ('Entregado', 0, GETDATE(), GETDATE()), 
  ('Cancelado', 0, GETDATE(), GETDATE());

INSERT INTO orders (
  number, issue_date, delivery_date, 
  comments, total, status_id, supplier_id, 
  user_id, is_deleted, created_at, updated_at
) 
VALUES 
  (
    'OC-20230601-1', '2023-06-01', '2023-06-10', 
    'Contactar con el encargado de logística.', 
    517999.95, 1, 1, 1, 0, GETDATE(), GETDATE()
  ), 
  (
    'OC-20230615-1', '2023-06-15', '2023-06-25', 
    'Entregar en el almacén central.', 
    707999.95, 1, 2, 2, 0, GETDATE(), GETDATE()
  ), 
  (
    'OC-20230705-1', '2023-07-05', '2023-07-15', 
    'Coordinar entrega con el departamento de compras.', 
    178999.97, 1, 3, 3, 0, GETDATE(), GETDATE()
  ), 
  (
    'OC-20230801-1', '2023-08-01', '2023-08-11', 
    'Enviar a la sucursal de Mendoza.', 
    3384999.95, 2, 4, 4, 0, GETDATE(), GETDATE()
  ), 
  (
    'OC-20230910-1', '2023-09-10', '2023-09-20', 
    'Prioridad en la entrega por necesidades urgentes.', 
    407999.99, 2, 5, 5, 0, GETDATE(), GETDATE()
  ), 
  (
    'OC-20231001-1', '2023-10-01', '2023-10-11', 
    'Entregar antes de la fecha acordada.', 
    983799.98, 2, 6, 6, 0, GETDATE(), GETDATE()
  ), 
  (
    'OC-20231105-1', '2023-11-05', '2023-11-15', 
    'Seguir protocolos de seguridad al entregar en planta.', 
    179398.99, 3, 7, 7, 0, GETDATE(), GETDATE()
  ), 
  (
    'OC-20231201-1', '2023-12-01', '2023-12-11', 
    'Entrega rápida para eventos promocionales.', 
    464999.95, 3, 8, 8, 0, GETDATE(), GETDATE()
  ), 
  (
    'OC-20231225-1', '2023-12-25', '2024-01-04', 
    'Coordinar entrega con el área de eventos.', 
    73739.99, 4, 9, 9, 0, GETDATE(), GETDATE()
  ), 
  (
    'OC-20240101-1', '2024-01-01', '2024-01-10', 
    'Entregar productos antes del lanzamiento.', 
    1219649.95, 4, 10, 10, 0, GETDATE(), GETDATE()
  );
  
INSERT INTO order_details (
  order_id, product_id, quantity, price, is_deleted, created_at, updated_at
) 
VALUES 
  (1, 1, 3, 159999.99, 0, GETDATE(), GETDATE()), 
  (1, 2, 2, 18999.99, 0, GETDATE(), GETDATE()), 
  (2, 3, 1, 255999.99, 0, GETDATE(), GETDATE()), 
  (2, 4, 4, 112999.99, 0, GETDATE(), GETDATE()), 
  (3, 5, 2, 78999.99, 0, GETDATE(), GETDATE()), 
  (3, 6, 1, 20999.99, 0, GETDATE(), GETDATE()), 
  (4, 7, 3, 588999.99, 0, GETDATE(), GETDATE()), 
  (4, 8, 2, 808999.99, 0, GETDATE(), GETDATE()), 
  (5, 9, 5, 59900.00, 0, GETDATE(), GETDATE()), 
  (5, 10, 1, 108499.99, 0, GETDATE(), GETDATE()), 
  (6, 11, 2, 19999.99, 0, GETDATE(), GETDATE()), 
  (6, 12, 3, 314600.00, 0, GETDATE(), GETDATE()), 
  (7, 13, 4, 36100.00, 0, GETDATE(), GETDATE()), 
  (7, 14, 1, 34998.99, 0, GETDATE(), GETDATE()), 
  (8, 15, 3, 2999.99, 0, GETDATE(), GETDATE()), 
  (8, 16, 2, 227999.99, 0, GETDATE(), GETDATE()), 
  (9, 17, 1, 29999.99, 0, GETDATE(), GETDATE()), 
  (9, 18, 2, 21870.00, 0, GETDATE(), GETDATE()), 
  (10, 19, 3, 196549.99, 0, GETDATE(), GETDATE()), 
  (10, 20, 2, 314999.99, 0, GETDATE(), GETDATE());
