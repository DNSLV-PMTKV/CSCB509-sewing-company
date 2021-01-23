package com.example.sewing.controller;

import java.util.List;

import com.example.sewing.dto.EmployeeStockDto;
import com.example.sewing.dto.EmployeeStocksMadeDto;
import com.example.sewing.dto.StocksMadeCountDto;
import com.example.sewing.service.EmployeeStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	List<EmployeeStocksMadeDto> getAllEmployeeStocksMade() {
		return service.getAllEmployeeStocksMade();
	}

	@GetMapping("stock-count")
	List<StocksMadeCountDto> getStcksMadeCount() {
		return service.getStocksMadeCount();
	}
}
