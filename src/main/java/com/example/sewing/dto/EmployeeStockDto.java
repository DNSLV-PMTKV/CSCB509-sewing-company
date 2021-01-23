package com.example.sewing.dto;

import java.time.Instant;

public class EmployeeStockDto {
	private Long id;
	private SimpleEmployeeDto employee;
	private SimpleStockDto stock;
	private Integer count;
	private Instant createdTs = Instant.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SimpleEmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(SimpleEmployeeDto employee) {
		this.employee = employee;
	}

	public SimpleStockDto getStock() {
		return stock;
	}

	public void setStock(SimpleStockDto stock) {
		this.stock = stock;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Instant getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Instant createdTs) {
		this.createdTs = createdTs;
	}

}
