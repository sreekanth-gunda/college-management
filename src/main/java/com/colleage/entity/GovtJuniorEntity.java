package com.colleage.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GovtJuniorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "student name should not be null or empty")
	@NotBlank
	private String name;

	@NotNull(message = "student group should not be null or empty")
	@NotBlank
	@Column(name = "group_name")
	private String group;

	@NotNull(message = "rollNo should not be null")
	@NotBlank
	@Column(unique = true)
	private String rollNo;

	@NotNull(message = "campus should not be null or empty")
	@NotBlank
	private String campus;

}