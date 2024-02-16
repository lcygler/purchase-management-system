package com.asj.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email is not valid", regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
	@Size(max = 255, message = "Email must be less than {max} characters")
	private String email;
	
	@NotBlank(message = "Password cannot be blank")
	@Size(max = 255, message = "Password must be less than {max} characters")
	private String password;

	public UserDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", password=" + password + "]";
	}

}
