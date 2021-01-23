package com.example.sewing.repository;

import com.example.sewing.entity.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock, Long> {

}
