package app.spring.patient_management_system.validation;
//custom exception for patient
public class PatientIdNotFound extends Exception{

	public PatientIdNotFound(String message) {
		super(message);
	}
}
