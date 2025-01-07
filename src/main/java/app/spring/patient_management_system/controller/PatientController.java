package app.spring.patient_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;

import app.spring.patient_management_system.entity.Appointment;
import app.spring.patient_management_system.entity.Doctor;
import app.spring.patient_management_system.entity.Medicine;
import app.spring.patient_management_system.entity.Patient;
import app.spring.patient_management_system.entity.User;
import app.spring.patient_management_system.repository.UserRepository;
import app.spring.patient_management_system.service.AppointmentMedicineService;
import app.spring.patient_management_system.service.DoctorPatientService;
import app.spring.patient_management_system.validation.AppointmentIdNotFound;
import jakarta.validation.Valid;

//patient controller
@Controller
@RequestMapping("/dashboard")
public class PatientController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AppointmentMedicineService appointmentMedicineService;
	
	@Autowired
	private DoctorPatientService doctorPatientService;

	public PatientController(AppointmentMedicineService appointmentMedicineService,
			DoctorPatientService doctorPatientService) {
		super();
		this.appointmentMedicineService = appointmentMedicineService;
		this.doctorPatientService = doctorPatientService;
	}

	@GetMapping
    public String displayDashboard(Model model){
		String user= returnUsername();
        model.addAttribute("userDetails", user);
        return "dashboard";
    }
	
	private String returnUsername() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
		User users = userRepository.findByEmail(user.getUsername());
		return users.getName();
	}
	
	/*Patient CRUD Operations*/
	@GetMapping("/patientlist")
	public String allpatients(Patient patient,Model model) {
		model.addAttribute("patients", appointmentMedicineService.getAllPatients());
		return "patient_all";
	}
	
	@GetMapping("/patients")
	public String addPatient(Patient patient,Model model) {
		model.addAttribute("patient", new Patient());
		return "patient_form";
	}
	
	@PostMapping("/processpatient")
	public String processPatient(@Valid Patient patient,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "patient_form";
		}
		appointmentMedicineService.save(patient);
		return "redirect:patientlist";
	}
	
	@RequestMapping("/deletepatient/{id}")
	public String  deletePatient(@PathVariable("id") Long id ) {
		appointmentMedicineService.deleteById(id);
		return "redirect:/dashboard/patientlist";
	}
	
	@GetMapping("/updatepatient/{id}")
	public String updatePatient(@PathVariable("id") Long id,Model model) throws Exception {
		model.addAttribute("patient", appointmentMedicineService.getPatientById(id));
		return "patient_update";
	}
	
	@PostMapping("/processpatient/{id}")
	public String processUpdatePatient(@PathVariable("id") Long id,@Valid@ModelAttribute("patient") Patient patient,BindingResult result) throws Exception {
		if(result.hasErrors()) {
			return "patient_update";
		}
		Patient existPatient= appointmentMedicineService.getPatientById(id);
		existPatient.setId(id);
		existPatient.setName(patient.getName());
		existPatient.setAge(patient.getAge());
		existPatient.setGender(patient.getGender());
		existPatient.setDisease(patient.getDisease());
		existPatient.setPhoneNo(patient.getPhoneNo());
		appointmentMedicineService.update(existPatient);
		return "redirect:/dashboard/patientlist";
	}
	
	@GetMapping("/schedulelist")
	public String allSchedule(Appointment appointment,Model model) {
		model.addAttribute("appointments", doctorPatientService.getAllAppointments());
		return "schedule_all_patient";
	}
		
	@GetMapping("/medicinelist")
	public String allmedicine(Medicine medicine, Model model) {
		model.addAttribute("medicines", doctorPatientService.getAllmedicines());
		return "medicine_all_patient";
	}
	
	@GetMapping("/patienthistory")
	public String getHistoryString(Appointment appointment) {
		return "patient_history";
	}
	
	@GetMapping("/patienthistory/{id}")
	public String getHistory(@PathVariable("id") Long id,Appointment appointment,Model model) throws AppointmentIdNotFound {
		model.addAttribute("appointment", doctorPatientService.getScheduleById(id));
		return "patient_history";
	}
	
	
}
