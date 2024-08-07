package com.kalaiselvan.springbootsecurity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
	@NotBlank(message = "Address is required")
	@Size(max = 255, message = "Address cannot exceed 255 characters")
	private String street;

	@NotBlank(message = "City is required")
	@Size(max = 50, message = "City cannot exceed 50 characters")
	private String city;

	@NotBlank(message = "State is required")
	@Size(max = 50, message = "State cannot exceed 50 characters")
	private String state;

	@NotBlank(message = "Zip code is required")
	@Size(max = 10, message = "Zip code cannot exceed 10 characters")
	private String zipCode;

	@NotBlank(message = "Country is required")
	@Size(max = 50, message = "Country cannot exceed 50 characters")
	private String country;
}
