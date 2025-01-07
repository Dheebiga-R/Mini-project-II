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
@Table(name="appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="date",nullable=false)
	@NotBlank(message="Fill this field")
	private String date;
	
	@Column(name="time",nullable=false)
	@NotBlank(message="Fill this field")
	private String time;
	@NotBlank(message="Fill this field")
	private String disease;
	
	@Column(name="doctor",nullable=false)
	@NotBlank(message="Fill this field")
	private String doctor;
}
