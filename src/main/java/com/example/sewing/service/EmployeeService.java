package com.example.sewing.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import com.example.sewing.dto.EmployeeDetailsDto;
import com.example.sewing.dto.SalaryDto;
import com.example.sewing.entity.Company;
import com.example.sewing.entity.Employee;
import com.example.sewing.exceptions.DoesNotExistsException;
import com.example.sewing.exceptions.InvalidInputException;
import com.example.sewing.repository.EmployeeRepo;
import com.example.sewing.tools.ObjectConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo repository;

	@Autowired
	private CompanyService companyService;

	public EmployeeDetailsDto createEmployee(EmployeeDetailsDto dto) {
		Employee employee = ObjectConverter.convertObject(dto, Employee.class);
		Employee saved = repository.save(employee);
		EmployeeDetailsDto o = ObjectConverter.convertObject(saved, EmployeeDetailsDto.class);
		o.setCompany(companyService.getCompany(saved.getCompany().getId()));
		return o;
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
		return ObjectConverter.convertObject(repository.save(employee), EmployeeDetailsDto.class);
	}

	public EmployeeDetailsDto setEmployeeSalary(SalaryDto salary, Long employeeId) {
		Optional<Employee> existing = repository.findById(employeeId);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException(String.format("Employee with ID: %d does not exist.", employeeId));
		}
		Employee employee = existing.get();
		Instant now = Instant.now();
		Instant sixMonths = now.minus(180, ChronoUnit.DAYS);
		if (employee.getHiredTs().isAfter(sixMonths) && salary.getSalary().compareTo(new BigDecimal("1000")) > 0) {
			throw new InvalidInputException(String.format(
					"Employee with ID %d is still inexperienced(<6 months) and can't get salary greater than 1000.",
					employeeId));
		}
		employee.setSalary(salary.getSalary());
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
