package app.spring.patient_management_system.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import app.spring.patient_management_system.DTO.UserRegisteredDTO;
import app.spring.patient_management_system.entity.User;

public interface UserService extends UserDetailsService{

	User save(UserRegisteredDTO userRegisteredDTO);

}
