package com.example.sewing.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sewing.dto.EmployeeDetailsDto;
import com.example.sewing.dto.EmployeeStockDto;
import com.example.sewing.dto.EmployeeStocksMadeDto;
import com.example.sewing.dto.IncomeDto;
import com.example.sewing.dto.OutcomeDto;
import com.example.sewing.dto.ProfitDto;
import com.example.sewing.dto.SimpleEmployeeDto;
import com.example.sewing.dto.SimpleStockDto;
import com.example.sewing.dto.StockCountDto;
import com.example.sewing.dto.StocksMadeCountDto;
import com.example.sewing.entity.EmployeeStock;
import com.example.sewing.entity.Stock;
import com.example.sewing.repository.EmployeeStockRepo;
import com.example.sewing.tools.ObjectConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeStockService {

	@Autowired
	private EmployeeStockRepo repository;

	@Autowired
	private EmployeeService employeeService;

	public EmployeeStockDto createEmployeeStock(EmployeeStockDto dto) {
		EmployeeStock employeeStock = ObjectConverter.convertObject(dto, EmployeeStock.class);
		EmployeeStock saved = repository.save(employeeStock);
		return ObjectConverter.convertObject(saved, EmployeeStockDto.class);
	}

	public IncomeDto getIncome() {
		List<EmployeeStock> all = repository.findAll();
		Double income = 0D;
		for (EmployeeStock employeeStock : all) {
			Double stockPrice = employeeStock.getStock().getSellPrice().doubleValue();
			income += stockPrice * employeeStock.getCount();
		}
		BigDecimal toBD = new BigDecimal(Double.toString(income));
		toBD.setScale(2, BigDecimal.ROUND_HALF_UP);
		return new IncomeDto(toBD);
	}

	public OutcomeDto getOutcome() {
		List<EmployeeStock> all = repository.findAll();
		List<EmployeeDetailsDto> employees = employeeService.getAllEmployees();
		Double outcome = 0D;
		for (EmployeeStock employeeStock : all) {
			Double stockCreatePrice = employeeStock.getStock().getCreatePrice().doubleValue();
			outcome += (stockCreatePrice * employeeStock.getCount());
		}
		for (EmployeeDetailsDto empl : employees) {
			outcome += empl.getSalary().doubleValue();
		}
		BigDecimal toBD = new BigDecimal(Double.toString(outcome));
		toBD.setScale(2, BigDecimal.ROUND_HALF_UP);
		return new OutcomeDto(toBD);
	}

	public ProfitDto getProfit() {
		IncomeDto income = getIncome();
		OutcomeDto outcome = getOutcome();
		return new ProfitDto(income.getIncome().subtract(outcome.getOutcome()));
	}

	public ProfitDto getProfitAfterTaxes() {
		ProfitDto cleanProfit = getProfit();
		if (cleanProfit.getProfit().compareTo(new BigDecimal(0)) > 0)
			return new ProfitDto(cleanProfit.getProfit().multiply(new BigDecimal(1.0 / 5.0)));
		return new ProfitDto(new BigDecimal("0"));
	}

	public List<EmployeeStocksMadeDto> getAllEmployeeStocksMade() {
		List<EmployeeDetailsDto> employees = employeeService.getAllEmployees();
		List<EmployeeStocksMadeDto> result = new ArrayList<>();
		for (EmployeeDetailsDto employee : employees) {
			EmployeeStocksMadeDto entry = new EmployeeStocksMadeDto();
			List<EmployeeStock> employeeStocks = repository.findAllByEmployeeId(employee.getId());
			Map<Stock, Integer> stockCounter = new HashMap<>();
			for (EmployeeStock stock : employeeStocks) {
				if (!stockCounter.containsKey(stock.getStock())) {
					stockCounter.put(stock.getStock(), stock.getCount());
				} else {
					stockCounter.put(stock.getStock(), stockCounter.get(stock.getStock()) + stock.getCount());
				}
			}
			List<StockCountDto> dtos = new ArrayList<>();
			for (Map.Entry<Stock, Integer> map : stockCounter.entrySet()) {
				dtos.add(new StockCountDto(ObjectConverter.convertObject(map.getKey(), SimpleStockDto.class),
						map.getValue()));
			}
			entry.setEmployee(ObjectConverter.convertObject(employee, SimpleEmployeeDto.class));
			entry.setStocks(dtos);
			result.add(entry);
		}
		return result;
	}

	public List<StocksMadeCountDto> getStocksMadeCount() {
		return repository.getStocksMadeCount();
	}

}
