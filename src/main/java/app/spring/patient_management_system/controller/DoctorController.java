package app.spring.patient_management_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.spring.patient_management_system.entity.Appointment;
import app.spring.patient_management_system.entity.Doctor;
import app.spring.patient_management_system.entity.Medicine;
import app.spring.patient_management_system.entity.User;
import app.spring.patient_management_system.repository.UserRepository;
import app.spring.patient_management_system.service.AppointmentMedicineService;
import app.spring.patient_management_system.service.DoctorPatientService;
import jakarta.validation.Valid;

//Doctor controller
@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/doctorScreen")
public class DoctorController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DoctorPatientService doctorPatientService;
	
	
	public DoctorController(UserRepository userRepository, DoctorPatientService doctorPatientService) {
		super();
		this.userRepository = userRepository;
		this.doctorPatientService = doctorPatientService;
	}

	@GetMapping
    public String displayDashboard(Model model){
		String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "doctorScreen";
    }
	
	private String returnUsername() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
		User users = userRepository.findByEmail(user.getUsername());
		return users.getName();
	}
	
	/*Doctor CRUD Operations*/ 
	@GetMapping("/doctorlist")
	public String allDoctors(Doctor doctor,Model model) {
		model.addAttribute("doctors", doctorPatientService.getAllDoctors());
		return "doctor_all";
	}
	
	@GetMapping("/doctors")
	public String addDoctor(Doctor doctor,Model model) {
		model.addAttribute("doctor", new Doctor());
		return "doctor_form";
	}
	
	@PostMapping("/processdoctor")
	public String processDoctor(@Valid Doctor doctor,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "doctor_form";
		}
		doctorPatientService.save(doctor);
		return "redirect:doctorlist";
	}
	
	@RequestMapping("/deletedoctor/{id}")
	public String  deleteDoctor(@PathVariable("id") Long id ) {
		doctorPatientService.deleteById(id);
		return "redirect:/doctorScreen/doctorlist";
	}
	
	@GetMapping("/updatedoctor/{id}")
	public String updateDoctor(@PathVariable("id") Long id,Model model) throws Exception {
		model.addAttribute("doctor", doctorPatientService.getDoctorById(id));
		return "doctor_update";
	}
	
	@PostMapping("/processdoctor/{id}")
	public String processUpdateDoctor(@PathVariable("id") Long id,@Valid@ModelAttribute("doctor") Doctor doctor,BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return "doctor_update";
		}
		Doctor existDoctor = doctorPatientService.getDoctorById(id);
		existDoctor.setId(id);
		existDoctor.setExperience(doctor.getExperience());
		existDoctor.setName(doctor.getName());
		existDoctor.setQualification(doctor.getQualification());
		existDoctor.setSpecialist(doctor.getSpecialist());
		doctorPatientService.update(existDoctor);
		return "redirect:/doctorScreen/doctorlist";
	}
	
	/*Appointment CRUD operations*/
	@GetMapping("/schedulelist")
	public String allSchedule(Appointment appointment,Model model) {
		model.addAttribute("appointments", doctorPatientService.getAllAppointments());
		return "schedule_all";
	}
	
	@GetMapping("/schedules")
	public String addSchedule(Appointment appointment,Model model) {
		model.addAttribute("appointment", new Appointment());
		return "schedule_form";
	}
	
	@PostMapping("/processschedule")
	public String processSchedule(@Valid Appointment appointment,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "schedule_form";
		}
		doctorPatientService.save(appointment);
		return "redirect:schedulelist";
	}
	
	@RequestMapping("/deleteschedule/{id}")
	public String  deleteSchedule(@PathVariable("id") Long id ) {
		doctorPatientService.deleteByScheduleId(id);
		return "redirect:/doctorScreen/schedulelist";
	}
	
	@GetMapping("/updateschedule/{id}")
	public String updateSchedule(@PathVariable("id") Long id,Model model) throws Exception {
		model.addAttribute("appointment", doctorPatientService.getScheduleById(id));
		return "schedule_update";
	}
	
	@PostMapping("/processschedule/{id}")
	public String processUpdateSchedule(@PathVariable("id") Long id,@Valid@ModelAttribute("appointment")Appointment appointment,BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return "schedule_update";
		}
		Appointment existAppointment = doctorPatientService.getScheduleById(id);
		existAppointment.setId(id);
		existAppointment.setDate(appointment.getDate());
		existAppointment.setTime(appointment.getTime());
		existAppointment.setDisease(appointment.getDisease());
		existAppointment.setDoctor(appointment.getDoctor());
		doctorPatientService.updateSchedule(existAppointment);
		return "redirect:/doctorScreen/schedulelist";
	}
	
	/*Medicine CRUD operations*/
	@GetMapping("/medicinelist")
	public String allmedicine(Medicine medicine,Model model) {
		model.addAttribute("medicines", doctorPatientService.getAllmedicines());
		return "medicine_all";
	}
	
	@GetMapping("/medicines")
	public String addmedicine(Medicine medicine,Model model) {
		model.addAttribute("medicne", new Medicine());
		return "medicine_form";
	}
	
	@PostMapping("/processmedicine")
	public String processMedicine(@Valid Medicine medicine,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "medicine_form";
		}
		doctorPatientService.save(medicine);
		return "redirect:medicinelist";
	}
	
	@RequestMapping("/deletemedicine/{id}")
	public String  deleteMedicine(@PathVariable("id") Long id ) {
		doctorPatientService.deleteByMedicineId(id);
		return "redirect:/doctorScreen/medicinelist";
	}
	
	@GetMapping("/updatemedicine/{id}")
	public String updateMedicine(@PathVariable("id") Long id,Model model) throws Exception {
		model.addAttribute("medicine", doctorPatientService.getMedicineById(id));
		return "medicine_update";
	}
	
	@PostMapping("/processmedicine/{id}")
	public String processUpdateMedicine(@PathVariable("id") Long id,@Valid@ModelAttribute("medicine")Medicine medicine,BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return "medicine_update";
		}
		Medicine existmedicine = doctorPatientService.getMedicineById(id);
		existmedicine.setId(id);
		existmedicine.setMedicineName(medicine.getMedicineName());
		existmedicine.setMfgDate(medicine.getMfgDate());
		existmedicine.setExpireDate(medicine.getExpireDate());
		existmedicine.setMedicineFor(medicine.getMedicineFor());
		doctorPatientService.updateMedicine(existmedicine);
		return "redirect:/doctorScreen/medicinelist";
	}
}
