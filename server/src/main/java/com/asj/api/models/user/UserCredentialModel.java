package com.asj.api.models.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_credentials")
public class UserCredentialModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull(message = "User cannot be null")
	private UserModel user;

	@Column(nullable = false, length = 255)
	@NotBlank(message = "Password cannot be blank")
	@Size(max = 255, message = "Password must be less than {max} characters")
	private String password;

	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	public UserCredentialModel() {
	}

	public UserCredentialModel(Integer id, UserModel user, String password, Boolean isDeleted, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.user = user;
		this.isDeleted = isDeleted;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "UserCredentialModel [id=" + id + ", user=" + user + ", password=" + password + ", isDeleted="
				+ isDeleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
