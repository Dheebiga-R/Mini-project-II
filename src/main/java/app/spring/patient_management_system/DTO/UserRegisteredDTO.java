package app.spring.patient_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//DTO registration to get data's from user and save it to entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredDTO {

    private String name;
	
	private String email_id;
	
	private String password;
	
	private String role;
	
}
