package app.spring.patient_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.spring.patient_management_system.DTO.UserLoginDTO;
import app.spring.patient_management_system.DTO.UserRegisteredDTO;
import app.spring.patient_management_system.service.UserService;
import app.spring.patient_management_system.service.UserServiceImpl;
//login controller
@Controller
@RequestMapping("/login")
public class LoginController {
	

	@Autowired
	private UserServiceImpl userService;

    public LoginController(UserServiceImpl userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }
    
	@GetMapping
	public String login() {
		return "login";
	}
	
	@PostMapping
	public void  loginUser(@ModelAttribute("user") 
	UserLoginDTO userLoginDTO) {
	 userService.loadUserByUsername(userLoginDTO.getUsername());
	}
}
