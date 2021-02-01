package com.example.sewing.controller;

import com.example.sewing.dto.EmployeeStockDto;
import com.example.sewing.service.EmployeeStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee-stock")
public class EmployeeStockController {
	@Autowired
	private EmployeeStockService service;

	@PostMapping
	EmployeeStockDto addEmployeeStock(@RequestBody EmployeeStockDto dto) {
		return service.createEmployeeStock(dto);
	}
}
