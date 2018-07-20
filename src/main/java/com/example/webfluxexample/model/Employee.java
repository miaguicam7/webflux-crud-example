package com.example.webfluxexample.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
//@Getter
//@Setter
//@Builder
//@ToString
//@Accessors(fluent = true)
@Document(collection = "employees")
public class Employee {
	@Id
	private String id;

	@NotBlank
	@Size(max = 140)
	private String name;

	@NotBlank
	private String role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
