package app.spring.patient_management_system.service;

import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.spring.patient_management_system.entity.Appointment;
import app.spring.patient_management_system.entity.Doctor;
import app.spring.patient_management_system.entity.Medicine;
import app.spring.patient_management_system.repository.AppointmentRepository;
import app.spring.patient_management_system.repository.DoctorRepository;
import app.spring.patient_management_system.repository.MedicineRepository;
import app.spring.patient_management_system.repository.PatientRepository;
import app.spring.patient_management_system.validation.AppointmentIdNotFound;
import app.spring.patient_management_system.validation.DoctorIdNotFound;
import app.spring.patient_management_system.validation.MedicineIdNotFound;
//Basic CRUD operation for doctor,appointment,medicine entity
@Service
public class DoctorPatientServiceImpl implements DoctorPatientService{

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private MedicineRepository medicineRepository;

	public DoctorPatientServiceImpl(DoctorRepository doctorRepository, 
			AppointmentRepository appointmentRepository,
			MedicineRepository medicineRepository) {
		super();
		this.doctorRepository = doctorRepository;
		this.appointmentRepository = appointmentRepository;
		this.medicineRepository = medicineRepository;
	}

	@Override
	public void save(Doctor doctor) {
		doctorRepository.save(doctor);
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		doctorRepository.deleteById(id);
		
	}

	@Override
	public Doctor getDoctorById(Long id) throws Exception {
		return doctorRepository.findById(id).orElseThrow(()-> new DoctorIdNotFound("Doctor not present"));
	}

	@Override
	public void update(Doctor existDoctor) {
		doctorRepository.save(existDoctor);
	}

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	@Override
	public void save(Appointment appointment) {
		appointmentRepository.save(appointment);
		
	}

	@Override
	public void deleteByScheduleId(Long id) {
		appointmentRepository.deleteById(id);
		
	}

	@Override
	public Appointment getScheduleById(Long id) throws AppointmentIdNotFound {
		return appointmentRepository.findById(id).orElseThrow(()->new AppointmentIdNotFound("No appointment Found"));
	}

	@Override
	public void updateSchedule(Appointment existAppointment) {
		appointmentRepository.save(existAppointment);
		
	}

	@Override
	public List<Medicine> getAllmedicines() {
		return medicineRepository.findAll();
	}

	@Override
	public void save(Medicine medicine) {
		medicineRepository.save(medicine);
	}

	@Override
	public void deleteByMedicineId(Long id) {
	    medicineRepository.deleteById(id);
	}

	@Override
	public Medicine getMedicineById(Long id) throws MedicineIdNotFound {
		return medicineRepository.findById(id).orElseThrow(()->new MedicineIdNotFound("Medicine not found"));
	}

	@Override
	public void updateMedicine(Medicine existmedicine) {
		medicineRepository.save(existmedicine);
	}
	
}
