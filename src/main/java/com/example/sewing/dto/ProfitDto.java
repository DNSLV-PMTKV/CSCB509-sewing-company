package com.example.sewing.dto;

import java.math.BigDecimal;

public class ProfitDto {

	private BigDecimal profit;

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public ProfitDto() {
	}

	public ProfitDto(BigDecimal profit) {
		this.profit = profit;
	}

}
