package com.example.sewing.dto;

public class StockCountDto {
	private SimpleStockDto stock;
	private Integer count;

	public StockCountDto(SimpleStockDto stock, Integer count) {
		this.stock = stock;
		this.count = count;
	}

	public StockCountDto() {
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

}
