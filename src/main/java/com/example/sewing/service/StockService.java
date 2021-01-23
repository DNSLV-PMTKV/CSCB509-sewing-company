package com.example.sewing.service;

import java.util.Optional;

import com.example.sewing.dto.StockDetailsDto;
import com.example.sewing.entity.Stock;
import com.example.sewing.exceptions.DoesNotExistsException;
import com.example.sewing.repository.StockRepo;
import com.example.sewing.tools.ObjectConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
	@Autowired
	private StockRepo repository;

	public StockDetailsDto createStock(StockDetailsDto dto) {
		Stock stock = ObjectConverter.convertObject(dto, Stock.class);
		Stock saved = repository.save(stock);
		return ObjectConverter.convertObject(saved, StockDetailsDto.class);
	}

	public StockDetailsDto updateStock(StockDetailsDto update, Long id) {
		Optional<Stock> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException(String.format("Stock with ID: %d does not exist.", id));
		}
		Stock stock = existing.get();
		stock.setColor(update.getColor() != null ? update.getColor() : stock.getColor());
		stock.setCreatePrice(update.getCreatePrice() != null ? update.getCreatePrice() : stock.getCreatePrice());
		stock.setSellPrice(update.getSellPrice() != null ? update.getSellPrice() : stock.getSellPrice());
		return ObjectConverter.convertObject(repository.save(stock), StockDetailsDto.class);
	}

	public StockDetailsDto getStock(Long id) {
		Optional<Stock> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException(String.format("Stock with ID: %d does not exist.", id));
		}
		return ObjectConverter.convertObject(repository.save(existing.get()), StockDetailsDto.class);
	}

	public void deleteStock(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}
}
