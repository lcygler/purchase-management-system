package com.asj.api.models.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

import com.asj.api.models.product.ProductModel;

@Entity
@Table(name = "order_details")
public class OrderDetailModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	@NotNull(message = "Order cannot be null")
	private OrderModel order;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	@NotNull(message = "Product cannot be null")
	private ProductModel product;

	@Column(nullable = false)
	@NotNull(message = "Quantity cannot be null")
	@Positive
	private Integer quantity;

	@Column(nullable = false)
	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be positive")
	private Double price;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	public OrderDetailModel() {
	}

	public OrderDetailModel(Integer id, OrderModel order, ProductModel product, Integer quantity, Double price,
			Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderModel getOrder() {
		return order;
	}

	public void setOrder(OrderModel order) {
		this.order = order;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "OrderDetailModel [id=" + id + ", order=" + order + ", product=" + product + ", quantity=" + quantity
				+ ", price=" + price + ", isDeleted=" + isDeleted + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

}
