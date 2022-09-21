package com.java.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.springboot.thymeleafdemo.entity.Employee;
import com.java.springboot.thymeleafdemo.service.EmployeeService;




@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load the employee data
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService=theEmployeeService;
	}
	
	
	// add mapping for "/list"
	
	@RequestMapping("/list")
	public String listEmployees(Model theModel) {
		// get employee from database
		
		List<Employee> theEmployees=employeeService.findAll();
		// add to the string model
		
		theModel.addAttribute("employees",theEmployees);
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String ShowFormForAdd(Model TheModel) {
		
		Employee theEmployee=new Employee();
		
		TheModel.addAttribute("employee",theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel) {
		
		// get the employee from service
		Employee theEmployee=employeeService.findById(theId);
		
		// set employee
		theModel.addAttribute("employee",theEmployee);
		
		//send over to our form
		
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		// save the employee
		employeeService.save(theEmployee);
		
		//use a redirect to prevent duplicate submissions
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		employeeService.deleteById(theId);
		
		
		return "redirect:/employees/list";
	}
}
