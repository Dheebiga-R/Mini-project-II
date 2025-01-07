package app.spring.patient_management_system.validation;
//custom exception for doctor
public class DoctorIdNotFound extends Exception{

	public DoctorIdNotFound(String message) {
		super(message);
	}
}
