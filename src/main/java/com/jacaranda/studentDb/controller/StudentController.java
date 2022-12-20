package com.jacaranda.studentDb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.studentDb.model.Student;
import com.jacaranda.studentDb.service.StudentService;



@Controller
public class StudentController {
	
	@Autowired
	private StudentService servicio;
	
	@GetMapping({"/","/listStudent"})
	public String listStudent(Model model) {
		model.addAttribute("lista", servicio.getStudents());
		
		return "listStudents";
	}
	
	@GetMapping("/addStudent")
	public String addStudent(Model model) {
		Student s =new Student();
		
		
		model.addAttribute("student", s);
		
		return "addStudent";
	}
	
	@PostMapping("/addStudent/submit")
	public String addStudentSubmit(@Validated @ModelAttribute("student") Student s ,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addStudent";
		}else {
			servicio.add(s);
			return "redirect:/listStudent";
		}

	}
	
	@GetMapping("/delStudent")
	public String Student(Model model,
				@RequestParam(name="id") Long id)
	{
		Student student=servicio.getStudent(id);
		model.addAttribute("student", student);
		
		return "deleteStudent";
	}
	
	@PostMapping("/delStudent/submit")
	public String delStudentSubmit(@ModelAttribute("student")Student student) {
		servicio.delete(student);
		
		return "redirect:/listStudent";
	}
	
	@GetMapping("/updateStudent")
	public String editStudent(Model model, @RequestParam(name="id")Long id) {
		
		Student estudiante = servicio.getStudent(id);
		model.addAttribute("student", estudiante);
		
		return "updateStudent";
	}
	
	@PostMapping("/updateStudent/Submit")
	public String listStudentseditMethod ( @ModelAttribute("student") Student student) {
		
		servicio.update(student);
			
		return "redirect:/listStudent";
	}
	
	
}
