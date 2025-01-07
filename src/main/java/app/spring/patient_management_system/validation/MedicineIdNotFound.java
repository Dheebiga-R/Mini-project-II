package app.spring.patient_management_system.validation;
//custom exception for medicine
public class MedicineIdNotFound extends Exception{

	public MedicineIdNotFound(String message) {
		super(message);
	}
}
