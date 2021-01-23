package com.example.sewing.dto;

import java.util.List;

public class EmployeeStocksMadeDto {
	private SimpleEmployeeDto employee;
	private List<StockCountDto> stocks;

	public SimpleEmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(SimpleEmployeeDto employee) {
		this.employee = employee;
	}

	public List<StockCountDto> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockCountDto> stocks) {
		this.stocks = stocks;
	}

}
