package app.spring.patient_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//DTO login to get data's from user and save it to entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    private String username;
	
	private String password;
}
