package com.example.sewing.controller;

import java.util.List;

import com.example.sewing.dto.EmployeeDetailsDto;
import com.example.sewing.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping
	public EmployeeDetailsDto createEmployee(@RequestBody EmployeeDetailsDto dto) {
		return service.createEmployee(dto);
	}

	@PutMapping("/{id}")
	public EmployeeDetailsDto updateEmployee(@RequestBody EmployeeDetailsDto dto, @PathVariable Long id) {
		return service.updateEmployee(dto, id);
	}

	@GetMapping("/{id}")
	public EmployeeDetailsDto getEmployee(@PathVariable Long id) {
		return service.getEmployee(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		service.deleteEmployee(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public List<EmployeeDetailsDto> getAllEmployees() {
		return service.getAllEmployees();
	}
}
