# Proyecto Integrador Final

Desarrollo de un **Sistema de Gestión de Compras** para manejar información de Proveedores, Productos y Órdenes de Compra.

## Instrucciones

Pasos necesarios para correr el proyecto localmente:

### Crear base de datos

- Crear una base de datos llamada `PurchaseOrderDB`:

```sql
  CREATE DATABASE PurchaseOrderDB;
  GO

  USE PurchaseOrderDB;
  GO
```

### Crear tablas

- Crear las tablas ejecutando el script: [db/scripts/mssql/create-tables.sql](https://github.com/lcygler/Bootcamp-ASJ-Proyecto/blob/main/20240209-Entrega-4/db/scripts/mssql/create-tables.sql)

### Insertar datos

- Insertar datos ejecutando el script: [db/scripts/mssql/bulk-insert.sql](https://github.com/lcygler/Bootcamp-ASJ-Proyecto/blob/main/20240209-Entrega-4/db/scripts/mssql/bulk-insert.sql)

### Ejecutar aplicación

- Ejecutar el servidor de Angular (puerto 4200):

```bash
  ng serve -o
```

- Ejecutar el servidor de Java (puerto 8080)

### Iniciar sesión

- Iniciar sesión con las siguientes credenciales:

```
  Usuario: admin@asjservicios.com
  Contraseña: admin
```

## API Reference

### Proveedores

#### Obtener todos los proveedores

```http
  GET /suppliers
```

#### Obtener un proveedor

```http
  GET /suppliers/${id}
```

| Parámetro | Tipo      | Descripción                                |
| :-------- | :-------- | :----------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del proveedor a buscar |

#### Crear proveedor

```http
  POST /suppliers
```

#### Actualizar proveedor

```http
  PUT /suppliers/${id}
```

| Parámetro | Tipo      | Descripción                                    |
| :-------- | :-------- | :--------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del proveedor a actualizar |

#### Actualizar proveedor parcialmente

```http
  PATCH /suppliers/${id}
```

| Parámetro | Tipo      | Descripción                                    |
| :-------- | :-------- | :--------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del proveedor a actualizar |

#### Eliminar proveedor

```http
  DELETE /suppliers/${id}
```

| Parámetro | Tipo      | Descripción                                  |
| :-------- | :-------- | :------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del proveedor a eliminar |

### Productos

#### Obtener todos los productos

```http
  GET /products
```

#### Obtener productos por proveedor

```http
  GET /products?supplierId={id}
```

| Parámetro | Tipo      | Descripción                       |
| :-------- | :-------- | :-------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del proveedor |

#### Obtener un producto

```http
  GET /products/${id}
```

| Parámetro | Tipo      | Descripción                               |
| :-------- | :-------- | :---------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del producto a buscar |

#### Crear producto

```http
  POST /products
```

#### Actualizar producto

```http
  PUT /products/${id}
```

| Parámetro | Tipo      | Descripción                                   |
| :-------- | :-------- | :-------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del producto a actualizar |

#### Actualizar producto parcialmente

```http
  PATCH /products/${id}
```

| Parámetro | Tipo      | Descripción                                   |
| :-------- | :-------- | :-------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del producto a actualizar |

#### Eliminar producto

```http
  DELETE /products/${id}
```

| Parámetro | Tipo      | Descripción                                 |
| :-------- | :-------- | :------------------------------------------ |
| `id`      | `Integer` | **Obligatorio**. ID del producto a eliminar |

### Ordenes de Compra

#### Obtener todas las ordenes

```http
  GET /orders
```

#### Obtener una orden

```http
  GET /orders/${id}
```

| Parámetro | Tipo      | Descripción                              |
| :-------- | :-------- | :--------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID de la orden a buscar |

#### Obtener detalles de una orden

```http
  GET /orders/{id}/details
```

| Parámetro | Tipo      | Descripción                              |
| :-------- | :-------- | :--------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID de la orden a buscar |

#### Obtener proveedores frecuentes

```http
  GET /orders/top-suppliers
```

#### Obtener ordenes totales por mes ($)

```http
  GET /orders/months-total
```

#### Crear orden

```http
  POST /orders
```

#### Actualizar orden

```http
  PUT /orders/${id}
```

| Parámetro | Tipo      | Descripción                                  |
| :-------- | :-------- | :------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID de la orden a actualizar |

#### Actualizar orden parcialmente

```http
  PATCH /orders/${id}
```

| Parámetro | Tipo      | Descripción                                  |
| :-------- | :-------- | :------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID de la orden a actualizar |

#### Eliminar orden

```http
  DELETE /orders/${id}
```

| Parámetro | Tipo      | Descripción                                |
| :-------- | :-------- | :----------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID de la orden a eliminar |

### Detalles de Ordenes de Compra

#### Obtener todos los detalles

```http
  GET /order-details
```

#### Obtener un detalle

```http
  GET /order-details/${id}
```

| Parámetro | Tipo      | Descripción                              |
| :-------- | :-------- | :--------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del detalle a buscar |

#### Obtener detalles de una orden

```http
  GET /order-details?orderId={id}
```

| Parámetro | Tipo      | Descripción                              |
| :-------- | :-------- | :--------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID de la orden a buscar |

#### Obtener productos más solicitados

```http
  GET /order-details/top-products
```

#### Crear detalle

```http
  POST /order-details
```

#### Actualizar detalle

```http
  PUT /order-details/${id}
```

| Parámetro | Tipo      | Descripción                                  |
| :-------- | :-------- | :------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del detalle a actualizar |

#### Actualizar detalle parcialmente

```http
  PATCH /order-details/${id}
```

| Parámetro | Tipo      | Descripción                                  |
| :-------- | :-------- | :------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del detalle a actualizar |

#### Eliminar detalle

```http
  DELETE /order-details/${id}
```

| Parámetro | Tipo      | Descripción                                |
| :-------- | :-------- | :----------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del detalle a eliminar |

### Usuarios

#### Obtener todos los usuarios

```http
  GET /users
```

#### Obtener un usuario

```http
  GET /users/${id}
```

| Parámetro | Tipo      | Descripción                              |
| :-------- | :-------- | :--------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del usuario a buscar |

#### Crear usuario

```http
  POST /users
```

#### Actualizar usuario

```http
  PUT /users/${id}
```

| Parámetro | Tipo      | Descripción                                  |
| :-------- | :-------- | :------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del usuario a actualizar |

#### Actualizar usuario parcialmente

```http
  PATCH /users/${id}
```

| Parámetro | Tipo      | Descripción                                  |
| :-------- | :-------- | :------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del usuario a actualizar |

#### Eliminar usuario

```http
  DELETE /users/${id}
```

| Parámetro | Tipo      | Descripción                                |
| :-------- | :-------- | :----------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del usuario a eliminar |

### Credenciales de Usuarios

#### Obtener todas las credenciales

```http
  GET /user-credentials
```

#### Obtener una credencial

```http
  GET /user-credentials/${id}
```

| Parámetro | Tipo      | Descripción                                   |
| :-------- | :-------- | :-------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID de la credencial a buscar |

#### Obtener credencial de un usuario

```http
  GET /user-credentials/user/{id}
```

| Parámetro | Tipo      | Descripción                              |
| :-------- | :-------- | :--------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del usuario a buscar |

#### Crear credencial

```http
  POST /user-credentials
```

#### Actualizar credencial

```http
  PUT /user-credentials/${id}
```

| Parámetro | Tipo      | Descripción                                       |
| :-------- | :-------- | :------------------------------------------------ |
| `id`      | `Integer` | **Obligatorio**. ID de la credencial a actualizar |

#### Actualizar credencial parcialmente

```http
  PATCH /user-credentials/${id}
```

| Parámetro | Tipo      | Descripción                                       |
| :-------- | :-------- | :------------------------------------------------ |
| `id`      | `Integer` | **Obligatorio**. ID de la credencial a actualizar |

#### Eliminar credencial

```http
  DELETE /user-credentials/${id}
```

| Parámetro | Tipo      | Descripción                                     |
| :-------- | :-------- | :---------------------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID de la credencial a eliminar |

## Desarrollado por

Este proyecto fue desarrollado por: **Leandro Cygler**
