package com.asj.api.models.user;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import com.asj.api.models.address.AddressModel;

@Entity
@Table(name = "users")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name", nullable = false, length = 255)
	@NotBlank(message = "First name cannot be blank")
	@Size(max = 255, message = "First name must be less than {max} characters")
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 255)
	@NotBlank(message = "Last name cannot be blank")
	@Size(max = 255, message = "Last name must be less than {max} characters")
	private String lastName;

	@Column(unique = true, nullable = false, length = 255)
	@NotBlank(message = "DNI cannot be blank")
	@Size(max = 255, message = "DNI must be less than {max} characters")
	private String dni;

	@Column(unique = true, nullable = false, length = 255)
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email is not valid", regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
	@Size(max = 255, message = "Email must be less than {max} characters")
	private String email;

	@Column(nullable = false, length = 255)
	@NotBlank(message = "Phone cannot be blank")
	@Size(max = 255, message = "Phone must be less than {max} characters")
	private String phone;

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable = false)
	@NotNull(message = "Genre cannot be null")
	private GenreModel genre;

	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	@NotNull(message = "Address cannot be null")
	private AddressModel address;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	@NotNull(message = "Role cannot be null")
	private RoleModel role;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	public UserModel() {
	}

	public UserModel(Integer id, String firstName, String lastName, String dni, String email, String phone,
			GenreModel genre, AddressModel address, RoleModel role, Boolean isDeleted, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.email = email;
		this.phone = phone;
		this.genre = genre;
		this.address = address;
		this.role = role;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public GenreModel getGenre() {
		return genre;
	}

	public void setGenre(GenreModel genre) {
		this.genre = genre;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
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
		return "UserModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dni=" + dni
				+ ", email=" + email + ", phone=" + phone + ", genre=" + genre + ", address=" + address + ", role="
				+ role + ", isDeleted=" + isDeleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
