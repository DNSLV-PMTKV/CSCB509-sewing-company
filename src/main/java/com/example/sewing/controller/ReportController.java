package com.example.sewing.controller;

import java.util.List;

import com.example.sewing.dto.EmployeeStocksMadeDto;
import com.example.sewing.dto.IncomeDto;
import com.example.sewing.dto.OutcomeDto;
import com.example.sewing.dto.ProfitDto;
import com.example.sewing.dto.StocksMadeCountDto;
import com.example.sewing.service.EmployeeStockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

	@Autowired
	private EmployeeStockService employeeStockService;

	@GetMapping("/income")
	public IncomeDto getIncome() {
		return employeeStockService.getIncome();
	}

	@GetMapping("/outcome")
	public OutcomeDto getOutcome() {
		return employeeStockService.getOutcome();
	}

	@GetMapping("/profit")
	public ProfitDto getCleanProfit() {
		return employeeStockService.getProfit();
	}

	@GetMapping("/clean-profit")
	public ProfitDto getProfit() {
		return employeeStockService.getProfitAfterTaxes();
	}

	@GetMapping("/employees-stock")
	public List<EmployeeStocksMadeDto> getAllEmployeeStocksMade() {
		return employeeStockService.getAllEmployeeStocksMade();
	}

	@GetMapping("/stock-count")
	public List<StocksMadeCountDto> getStcksMadeCount() {
		return employeeStockService.getStocksMadeCount();
	}
}
