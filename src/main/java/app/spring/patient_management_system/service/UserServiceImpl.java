package app.spring.patient_management_system.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.spring.patient_management_system.DTO.UserRegisteredDTO;
import app.spring.patient_management_system.entity.Role;
import app.spring.patient_management_system.entity.User;
import app.spring.patient_management_system.repository.RoleRepository;
import app.spring.patient_management_system.repository.UserRepository;
//for login and registration
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		User user = userRepository.findByEmail(email);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> list) {
		
		return list.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}
	
	public User save(UserRegisteredDTO userRegisteredDTO) {
	     Role role = new Role();
		if(userRegisteredDTO.getRole().equals("USER"))
		  role = roleRepository.findByRole("USER");
		else if(userRegisteredDTO.getRole().equals("ADMIN"))
		 role = roleRepository.findByRole("ADMIN");
		User user = new User();
		user.setEmail(userRegisteredDTO.getEmail_id());
		user.setName(userRegisteredDTO.getName());
		user.setPassword(passwordEncoder.encode(userRegisteredDTO.getPassword()));
        user.setRoles(Arrays.asList(role));		
		return userRepository.save(user);
	}
}
