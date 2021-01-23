package com.example.sewing.dto;

public class StocksMadeCountDto {
	private SimpleStockDto stock;
	private Long counter;

	public SimpleStockDto getStock() {
		return stock;
	}

	public void setStock(SimpleStockDto stock) {
		this.stock = stock;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}

	public StocksMadeCountDto() {
	}

	public StocksMadeCountDto(long id, String name, String color, long counter) {
		this.stock = new SimpleStockDto(id, name, color);
		this.counter = counter;
	}

}
