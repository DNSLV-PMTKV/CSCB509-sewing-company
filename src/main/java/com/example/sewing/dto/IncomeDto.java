package com.example.sewing.dto;

import java.math.BigDecimal;

public class IncomeDto {
	private BigDecimal income;

	public IncomeDto() {
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public IncomeDto(BigDecimal income) {
		this.income = income;
	}

	public IncomeDto(Double income) {
		this.income = new BigDecimal(income);
	}

}
