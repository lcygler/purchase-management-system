-- Create Tables
-- Addresses
CREATE TABLE countries (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE states (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  country_id INT NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE addresses (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  street VARCHAR(255) NOT NULL,
  number VARCHAR(20) NOT NULL,
  postal_code VARCHAR(20) NOT NULL,
  city VARCHAR(255) NOT NULL,
  state_id INT NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

-- Images
CREATE TABLE images (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  url VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

-- Users
CREATE TABLE genres (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE roles (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE users (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  dni VARCHAR(255) UNIQUE NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  phone VARCHAR(255) NOT NULL,
  genre_id INT NOT NULL,
  address_id INT NOT NULL,
  role_id INT NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE user_credentials (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  user_id INT NOT NULL,
  password VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

-- Suppliers
CREATE TABLE industries (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE vat_conditions (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE tax_information (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  cuit VARCHAR(255) UNIQUE NOT NULL,
  vat_condition_id INT NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE contact_details (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE suppliers (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  code VARCHAR(255) UNIQUE NOT NULL,
  business_name VARCHAR(255) NOT NULL,
  industry_id INT NOT NULL,
  website VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  image_id INT,
  address_id INT NOT NULL,
  tax_information_id INT NOT NULL,
  contact_detail_id INT NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

-- Products
CREATE TABLE categories (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE products (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  sku VARCHAR(255) UNIQUE NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price FLOAT NOT NULL,
  image_id INT,
  category_id INT NOT NULL,
  supplier_id INT NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

-- Orders
CREATE TABLE statuses (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE orders (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  number VARCHAR(255) UNIQUE NOT NULL,
  issue_date DATETIME DEFAULT (GETDATE()),
  delivery_date DATETIME NOT NULL,
  comments VARCHAR(255) NOT NULL,
  total FLOAT NOT NULL,
  status_id INT NOT NULL,
  supplier_id INT NOT NULL,
  user_id INT,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE order_details (
  id INT IDENTITY(1, 1) PRIMARY KEY,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  price FLOAT NOT NULL,
  is_deleted BIT NOT NULL DEFAULT 0,
  created_at DATETIME NOT NULL DEFAULT GETDATE(),
  updated_at DATETIME NOT NULL DEFAULT GETDATE()
)

-- Create Relationships
-- Addresses
ALTER TABLE 
  addresses 
ADD 
  CONSTRAINT fk_addresses_states FOREIGN KEY (state_id) REFERENCES states (id);

-- States
ALTER TABLE 
  states 
ADD 
  CONSTRAINT fk_states_countries FOREIGN KEY (country_id) REFERENCES countries (id);

-- Users
ALTER TABLE 
  users 
ADD 
  CONSTRAINT fk_users_genres FOREIGN KEY (genre_id) REFERENCES genres (id);

ALTER TABLE 
  users 
ADD 
  CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE 
  users 
ADD 
  CONSTRAINT fk_users_addresses FOREIGN KEY (address_id) REFERENCES addresses (id);

-- User_Credentials
ALTER TABLE 
  user_credentials 
ADD 
  CONSTRAINT fk_user_credentials_users FOREIGN KEY (user_id) REFERENCES users (id);

-- Suppliers
ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_industries FOREIGN KEY (industry_id) REFERENCES industries (id);

ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_images FOREIGN KEY (image_id) REFERENCES images (id);

ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_addresses FOREIGN KEY (address_id) REFERENCES addresses (id);

ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_tax_information FOREIGN KEY (tax_information_id) REFERENCES tax_information (id);

ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_contact_details FOREIGN KEY (contact_detail_id) REFERENCES contact_details (id);

-- Tax_Information
ALTER TABLE 
  tax_information 
ADD 
  CONSTRAINT fk_tax_information_vat_conditions FOREIGN KEY (vat_condition_id) REFERENCES vat_conditions (id);

-- Products
ALTER TABLE 
  products 
ADD 
  CONSTRAINT fk_products_images FOREIGN KEY (image_id) REFERENCES images (id);

ALTER TABLE 
  products 
ADD 
  CONSTRAINT fk_products_categories FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE 
  products 
ADD 
  CONSTRAINT fk_products_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

-- Orders
ALTER TABLE 
  orders 
ADD 
  CONSTRAINT fk_orders_statuses FOREIGN KEY (status_id) REFERENCES statuses (id);

ALTER TABLE 
  orders 
ADD 
  CONSTRAINT fk_orders_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

-- Order_Details
ALTER TABLE 
  order_details 
ADD 
  CONSTRAINT fk_order_details_orders FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE 
  order_details 
ADD 
  CONSTRAINT fk_order_details_products FOREIGN KEY (product_id) REFERENCES products (id);
