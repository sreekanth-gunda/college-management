package com.colleage.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class GovtJuniorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "student name shoud  not be null or empty.")
	@NotBlank
	private String name;
	@NotNull(message = "student group shoud  not be null or empty.")
	@NotBlank
	@Column(name = "group_name")
	private String group;
	@Positive(message="student rollNo must be positive only.")
	@NotNull(message = "student group shoud  not be null or empty.")
	@Column(unique = true)
	private String rollNo;
	@NotNull(message = "student group shoud  not be null or empty.")
	@NotBlank
	private String campus;

	@CreatedDate
	@Column(updatable = false)
	private LocalDate createdDate;

	@LastModifiedDate
	private LocalDate updatedDate;



}
