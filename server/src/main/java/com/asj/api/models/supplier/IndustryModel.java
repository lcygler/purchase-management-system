package com.asj.api.models.supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "industries")
public class IndustryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false, length = 255)
	@NotBlank(message = "Name cannot be blank")
	@Size(max = 255, message = "Name must be less than {max} characters")
	private String name;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	public IndustryModel() {
	}

	public IndustryModel(Integer id, String name, Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "IndustryModel [id=" + id + ", name=" + name + ", isDeleted=" + isDeleted + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
