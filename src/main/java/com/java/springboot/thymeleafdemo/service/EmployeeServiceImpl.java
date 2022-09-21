package com.java.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.java.springboot.thymeleafdemo.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl( EmployeeRepository theemployeeRepository) {
		employeeRepository=theemployeeRepository;
	}
	
	@Override
	
	public List<Employee> findAll() {
		
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	
	public Employee findById(int theId) {
		
		Optional<Employee> result=employeeRepository.findById(theId);
		 
		Employee theEmployee=null;
		
		if(result.isPresent()) {
			theEmployee=result.get();
		}
		else {
			throw new RuntimeException("Did not found employee id -" + theId);
		}
		
		return theEmployee;
	}

	@Override

	public void save(Employee thEmployee) {
		employeeRepository.save(thEmployee);
	}

	@Override
	
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);

	}

}
