package com.asj.api.models.supplier;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "tax_information")
public class TaxInformationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false, length = 255)
	@NotBlank(message = "CUIT cannot be blank")
	@Size(max = 255, message = "CUIT must be less than {max} characters")
	private String cuit;

	@ManyToOne
	@JoinColumn(name = "vat_condition_id", nullable = false)
	@NotNull(message = "Vat condition cannot be null")
	private VatConditionModel vatCondition;

	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	public TaxInformationModel() {
	}

	public TaxInformationModel(Integer id, String cuit, VatConditionModel vatCondition, Boolean isDeleted, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.cuit = cuit;
		this.vatCondition = vatCondition;
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

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public VatConditionModel getVatCondition() {
		return vatCondition;
	}

	public void setVatCondition(VatConditionModel vatCondition) {
		this.vatCondition = vatCondition;
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
		return "TaxInformationModel [id=" + id + ", cuit=" + cuit + ", vatCondition=" + vatCondition + ", isDeleted="
				+ isDeleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
