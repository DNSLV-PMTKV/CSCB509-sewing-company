package com.example.sewing.controller;

import com.example.sewing.dto.StockDetailsDto;
import com.example.sewing.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class StockController {
	@Autowired
	private StockService service;

	@PostMapping
	public StockDetailsDto createStock(@RequestBody StockDetailsDto dto) {
		return service.createStock(dto);
	}

	@PutMapping("/{id}")
	public StockDetailsDto updateStock(@RequestBody StockDetailsDto dto, @PathVariable Long id) {
		return service.updateStock(dto, id);
	}

	@GetMapping("/{id}")
	public StockDetailsDto getStock(@PathVariable Long id) {
		return service.getStock(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
		service.deleteStock(id);
		return ResponseEntity.ok().build();
	}
}
