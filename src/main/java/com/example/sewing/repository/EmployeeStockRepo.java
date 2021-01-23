package com.example.sewing.repository;

import java.util.List;

import com.example.sewing.dto.StocksMadeCountDto;
import com.example.sewing.entity.EmployeeStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeStockRepo extends JpaRepository<EmployeeStock, Long> {
	List<EmployeeStock> findAllByEmployeeId(Long employeeId);

	@Query("SELECT new com.example.sewing.dto.StocksMadeCountDto( " + "ST.id, ST.name, ST.color, sum(ES.count)) "
			+ "from EmployeeStock ES " + "join Stock ST on ST.id = ES.stock.id " + "group by ST.id")
	List<StocksMadeCountDto> getStocksMadeCount();

	// select employees.id,employees.full_name, stocks.id as stockID, stocks.name,
	// stocks.color, sum(employee_stocks.count)
	// from employee_stocks
	// join stocks ON stocks.id = stock_id
	// join employees on employees.id = employee_id
	// group by(employees.id, stocks.id)
	// order by(employees.id);

}
