package app.spring.patient_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="doctor_name",nullable=false)
	@NotBlank(message = "Fill this field")
	private String name;
	
	@Column(name="doctor_qualification",nullable=false)
	@NotBlank(message = "Fill this field")
	private String qualification;
	
	@Column(name="working_experience",nullable=false)
	@Min(2)
	private int experience;
	
	@Column(nullable=false)
	@NotBlank(message = "Fill this field")
	private String specialist;
}
