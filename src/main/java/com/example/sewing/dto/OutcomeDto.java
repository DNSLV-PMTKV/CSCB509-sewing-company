package com.example.sewing.dto;

import java.math.BigDecimal;

public class OutcomeDto {
	private BigDecimal outcome;

	public OutcomeDto() {
	}

	public OutcomeDto(BigDecimal outcome) {
		this.outcome = outcome;
	}

	public OutcomeDto(Double outcome) {
		this.outcome = new BigDecimal(outcome);
	}

	public BigDecimal getOutcome() {
		return outcome;
	}

	public void setOutcome(BigDecimal outcome) {
		this.outcome = outcome;
	}
}
