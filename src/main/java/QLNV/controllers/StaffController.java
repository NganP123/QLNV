package QLNV.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import QLNV.models.Depart;
import QLNV.models.Staff;
import QLNV.dtos.StaffDto;
import QLNV.services.StaffService;


@Controller
@RequestMapping("/staffs")
public class StaffController {
	@Autowired
	private StaffService staffService;
	
	@RequestMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("staffs", staffService.findAll());
		return "staffs/list";
	}
	@GetMapping("/add")
	public String add(ModelMap model) {
		StaffDto staff = new StaffDto();
		model.addAttribute("staffDto", staff);
		return "staffs/addOrEdit";
		
	}
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated StaffDto staffDto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Please input all required fields!!");
			model.addAttribute("staffDto", staffDto);
			return "staffs/addOrEdit";
		}
		
		if(staffDto.getId() != null && staffDto.getId() > 0) {
			model.addAttribute("message", "The staff updated!");
		}
		else {
			model.addAttribute("message", "The staff inserted!");
		}
		
		Path path = Paths.get("images/");
		try (InputStream inputStream = staffDto.getImage().getInputStream()){
			Files.copy(inputStream, path.resolve(staffDto.getImage().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			String filename = staffDto.getImage().getOriginalFilename();
			
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Error: " +e.getMessage());
		}
		Staff staff = new Staff();
		staff.setBirthday(staffDto.getBirthday());
		staff.setGender(staffDto.getGender());
		staff.setName(staffDto.getName());
		staff.setNote(staffDto.getNote());
		staff.setPhone(staffDto.getPhone());
		staff.setSalary(staffDto.getSalary());
		staff.setPhoto(staffDto.getImage().getOriginalFilename());
		Depart depart = new Depart();
		depart.setId(staffDto.getDepartId());
		staff.setDepart(depart);
		
		staffService.save(staff);
		
		model.addAttribute("staffDto", staffDto);
		return "staffs/addOrEdit";
	}
	
	
	/*
	 * @GetMapping("/edit/{id}") public String edit(ModelMap
	 * model, @PathVariable(name = "id") Long id) { Optional<Staff> otpStaff =
	 * staffService.findById(id); if (otpStaff.isPresent()) {
	 * model.addAttribute("staff", otpStaff.get()); }else { return list(model); }
	 * return "staffs/addOrEdit"; }
	 */
	@GetMapping("/delete/{id}")
	public String delete(ModelMap model, @PathVariable(name = "id") Long id) {
		staffService.deleteById(id);
		return list(model);
	}
	
	
	@ModelAttribute(name  = "departs")
	public List<Depart> getDeparts(){
		return staffService.findAllDeparts();
	}
	
	@RequestMapping("/find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String name) {
		List<Staff> list = staffService.findByNameLikeOrderByName(name);
		model.addAttribute("staffs",list);
		return "staffs/find";
	}
}
