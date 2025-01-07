package app.spring.patient_management_system.validation;
//custom exception for appointment
public class AppointmentIdNotFound extends Exception{
	
	public AppointmentIdNotFound(String message) {
		super(message);
	}

}
