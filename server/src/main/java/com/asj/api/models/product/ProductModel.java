package com.asj.api.models.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import com.asj.api.models.common.ImageModel;
import com.asj.api.models.supplier.SupplierModel;

@Entity
@Table(name = "products")
public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false, length = 255)
	@Size(max = 255, message = "SKU must be less than {max} characters")
	private String sku;

	@Column(nullable = false, length = 255)
	@NotBlank(message = "Name cannot be blank")
	@Size(max = 255, message = "Name must be less than {max} characters")
	private String name;

	@Column(nullable = false, length = 255)
	@NotBlank(message = "Description cannot be blank")
	@Size(max = 255, message = "Description must be less than {max} characters")
	private String description;

	@Column(nullable = false)
	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be positive")
	private Double price;

	@OneToOne
	@JoinColumn(name = "image_id")
	private ImageModel image;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@NotNull(message = "Category cannot be null")
	private CategoryModel category;

	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable = false)
	@NotNull(message = "Supplier cannot be null")
	private SupplierModel supplier;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	public ProductModel() {
	}

	public ProductModel(Integer id, String sku, String name, String description, Double price, ImageModel image,
			CategoryModel category, SupplierModel supplier, Boolean isDeleted, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.category = category;
		this.supplier = supplier;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ImageModel getImage() {
		return image;
	}

	public void setImage(ImageModel image) {
		this.image = image;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
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
		return "ProductModel [id=" + id + ", sku=" + sku + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", image=" + image + ", category=" + category + ", supplier=" + supplier
				+ ", isDeleted=" + isDeleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
