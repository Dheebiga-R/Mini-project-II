package app.spring.patient_management_system.service;

import java.util.List;

import app.spring.patient_management_system.entity.Appointment;
import app.spring.patient_management_system.entity.Doctor;
import app.spring.patient_management_system.entity.Medicine;
import app.spring.patient_management_system.validation.AppointmentIdNotFound;
import app.spring.patient_management_system.validation.MedicineIdNotFound;

public interface DoctorPatientService {

	void save(Doctor doctor);

	List<Doctor> getAllDoctors();

	void deleteById(Long id);

	Doctor getDoctorById(Long id) throws Exception;

	void update(Doctor existDoctor);

	List<Appointment> getAllAppointments();

	void save(Appointment appointment);

	void deleteByScheduleId(Long id);

    Appointment getScheduleById(Long id) throws AppointmentIdNotFound;

	void updateSchedule(Appointment existAppointment);

	List<Medicine> getAllmedicines();

	void save(Medicine medicine);

	void deleteByMedicineId(Long id);

	Medicine getMedicineById(Long id) throws MedicineIdNotFound;

	void updateMedicine(Medicine existmedicine);

}
