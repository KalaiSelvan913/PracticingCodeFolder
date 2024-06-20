package com.kalaiselvan.springbootsecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

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
