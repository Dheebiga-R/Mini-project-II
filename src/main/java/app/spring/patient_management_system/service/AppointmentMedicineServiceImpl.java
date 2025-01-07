package app.spring.patient_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.spring.patient_management_system.entity.Appointment;
import app.spring.patient_management_system.entity.Patient;
import app.spring.patient_management_system.repository.AppointmentRepository;
import app.spring.patient_management_system.repository.MedicineRepository;
import app.spring.patient_management_system.repository.PatientRepository;
import app.spring.patient_management_system.validation.PatientIdNotFound;
//performing basic CRUD operations
@Service
public class AppointmentMedicineServiceImpl implements AppointmentMedicineService{
	
	@Autowired
	private PatientRepository patientRepository;

	public AppointmentMedicineServiceImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public void save(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void deleteById(Long id) {
		patientRepository.deleteById(id);
	}

	@Override
	public Patient getPatientById(Long id) throws PatientIdNotFound {
		return patientRepository.findById(id).orElseThrow(()->new PatientIdNotFound("Patient not present"));
	}

	@Override
	public void update(Patient existPatient) {
		patientRepository.save(existPatient);
	}
	
	

}
