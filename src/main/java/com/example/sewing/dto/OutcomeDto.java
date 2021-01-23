package com.example.sewing.dto;

import java.math.BigDecimal;

public class OutcomeDto {
	private BigDecimal outcome;

	public OutcomeDto() {
	}

	public BigDecimal getIncome() {
		return outcome;
	}

	public void setIncome(BigDecimal outcome) {
		this.outcome = outcome;
	}

	public OutcomeDto(BigDecimal outcome) {
		this.outcome = outcome;
	}

	public OutcomeDto(Double outcome) {
		this.outcome = new BigDecimal(outcome);
	}
}
