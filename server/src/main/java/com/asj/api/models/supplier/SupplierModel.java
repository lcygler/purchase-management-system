package com.asj.api.models.supplier;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import com.asj.api.models.address.AddressModel;
import com.asj.api.models.common.ImageModel;

@Entity
@Table(name = "suppliers")
public class SupplierModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false, length = 255)
	@Size(max = 255, message = "Code must be less than {max} characters")
	private String code;

	@Column(name = "business_name", nullable = false, length = 255)
	@NotBlank(message = "Business name cannot be blank")
	@Size(max = 255, message = "Business name must be less than {max} characters")
	private String businessName;

	@ManyToOne
	@JoinColumn(name = "industry_id", nullable = false)
	@NotNull(message = "Industry cannot be null")
	private IndustryModel industry;

	@Column(nullable = false, length = 255)
	@NotBlank(message = "Website cannot be blank")
	@Pattern(message = "Website is not valid", regexp = "https?:\\/\\/(www\\.)?[\\-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([\\-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)")
	@Size(max = 255, message = "Website must be less than {max} characters")
	private String website;
	
	@Column(nullable = false, length = 255)
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email is not valid", regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
	@Size(max = 255, message = "Email must be less than {max} characters")
	private String email;

	@Column(nullable = false, length = 255)
	@NotBlank(message = "Phone cannot be blank")
	@Size(max = 255, message = "Phone must be less than {max} characters")
	private String phone;

	@OneToOne
	@JoinColumn(name = "image_id")
	private ImageModel image;

	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	@NotNull(message = "Address cannot be null")
	private AddressModel address;

	@OneToOne
	@JoinColumn(name = "tax_information_id", nullable = false)
	@NotNull(message = "Tax information cannot be null")
	private TaxInformationModel taxInformation;

	@OneToOne
	@JoinColumn(name = "contact_detail_id", nullable = false)
	@NotNull(message = "Contact details cannot be null")
	private ContactDetailModel contactDetails;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	public SupplierModel() {
	}

	public SupplierModel(Integer id, String code, String businessName, IndustryModel industry, String website,
			String email, String phone, ImageModel image, AddressModel address, TaxInformationModel taxInformation,
			ContactDetailModel contactDetails, Boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.code = code;
		this.businessName = businessName;
		this.industry = industry;
		this.website = website;
		this.email = email;
		this.phone = phone;
		this.image = image;
		this.address = address;
		this.taxInformation = taxInformation;
		this.contactDetails = contactDetails;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public IndustryModel getIndustry() {
		return industry;
	}

	public void setIndustry(IndustryModel industry) {
		this.industry = industry;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	public ImageModel getImage() {
		return image;
	}

	public void setImage(ImageModel image) {
		this.image = image;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public TaxInformationModel getTaxInformation() {
		return taxInformation;
	}

	public void setTaxInformation(TaxInformationModel taxInformation) {
		this.taxInformation = taxInformation;
	}

	public ContactDetailModel getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetailModel contactDetails) {
		this.contactDetails = contactDetails;
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
		return "SupplierModel [id=" + id + ", code=" + code + ", businessName=" + businessName + ", industry="
				+ industry + ", website=" + website + ", email=" + email + ", phone=" + phone + ", image=" + image
				+ ", address=" + address + ", taxInformation=" + taxInformation + ", contactDetails=" + contactDetails
				+ ", isDeleted=" + isDeleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}