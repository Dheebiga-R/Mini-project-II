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
@NoArgsConstructor
@AllArgsConstructor
@Table(name="medicine")
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="medicine_name",nullable=false)
	@NotBlank(message="Fill this field")
	private String medicineName;
	
	@Column(name="meanufacturer_date",nullable=false)
	@NotBlank(message="Fill this field")
	private String mfgDate;
	
	@Column(name="expire_date",nullable=false)
	@NotBlank(message="Fill this field")
	private String expireDate;
	
	@Column(name="medicine_for",nullable=false)
	@NotBlank(message="Fill this field")
	private String medicineFor;
	
}
