package com.example.sewing.dto;

import java.math.BigDecimal;

public class StockDetailsDto {
	private Long id;
	private String name;
	private String color;
	private BigDecimal sellPrice;
	private BigDecimal createPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public BigDecimal getCreatePrice() {
		return createPrice;
	}

	public void setCreatePrice(BigDecimal createPrice) {
		this.createPrice = createPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
