package app.spring.patient_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="patient_name",nullable =false)
	@NotBlank(message="Fill this field")
	private String name;
	
	@Column(name="patient_gender",nullable =false)
	@NotBlank(message="Fill this field")
	private String gender;
	
	@Column(name="patient_age",nullable =false)
	private int age;
	
	@Column(name="patient_phoneNo",nullable =false)
	private Long phoneNo;
	
	@Column(name="patient_disease",nullable =false)
	@NotBlank(message="Fill this field")
	private String disease;
}
