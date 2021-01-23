package com.example.sewing.service;

import java.util.List;
import java.util.Optional;

import com.example.sewing.dto.EmployeeDetailsDto;
import com.example.sewing.entity.Company;
import com.example.sewing.entity.Employee;
import com.example.sewing.exceptions.DoesNotExistsException;
import com.example.sewing.repository.EmployeeRepo;
import com.example.sewing.tools.ObjectConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo repository;

	public EmployeeDetailsDto createEmployee(EmployeeDetailsDto dto) {
		Employee employee = ObjectConverter.convertObject(dto, Employee.class);
		Employee saved = repository.save(employee);
		return ObjectConverter.convertObject(saved, EmployeeDetailsDto.class);
	}

	public EmployeeDetailsDto updateEmployee(EmployeeDetailsDto update, Long id) {
		Optional<Employee> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException(String.format("Employee with ID: %d does not exist.", id));
		}
		Employee employee = existing.get();
		employee.setFullName(update.getFullName() != null ? update.getFullName() : employee.getFullName());
		employee.setCompany(
				update.getCompany() != null ? ObjectConverter.convertObject(update.getCompany(), Company.class)
						: employee.getCompany());
		employee.setSalary(update.getSalary() != null ? update.getSalary() : employee.getSalary());
		return ObjectConverter.convertObject(repository.save(employee), EmployeeDetailsDto.class);
	}

	public EmployeeDetailsDto getEmployee(Long id) {
		Optional<Employee> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException(String.format("Employee with ID: %d does not exist.", id));
		}
		return ObjectConverter.convertObject(repository.save(existing.get()), EmployeeDetailsDto.class);
	}

	public void deleteEmployee(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public List<EmployeeDetailsDto> getAllEmployees() {
		List<Employee> employees = repository.findAll();
		return ObjectConverter.convertList(employees, EmployeeDetailsDto.class);
	}

}
