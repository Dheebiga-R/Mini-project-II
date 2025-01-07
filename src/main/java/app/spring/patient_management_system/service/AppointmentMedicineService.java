package app.spring.patient_management_system.service;

import java.util.List;

import app.spring.patient_management_system.entity.Patient;
import app.spring.patient_management_system.validation.PatientIdNotFound;

public interface AppointmentMedicineService {

	List<Patient> getAllPatients();

	void save(Patient patient);

	void deleteById(Long id);

	Patient getPatientById(Long id) throws PatientIdNotFound;

	void update(Patient existPatient);

}
