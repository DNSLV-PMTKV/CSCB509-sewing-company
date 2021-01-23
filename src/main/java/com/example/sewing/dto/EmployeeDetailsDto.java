package com.example.sewing.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class EmployeeDetailsDto {
	private Long id;
	private String fullName;
	private Instant hiredTs = Instant.now();
	private BigDecimal salary;
	private CompanyDetailsDto company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Instant getHiredTs() {
		return hiredTs;
	}

	public void setHiredTs(Instant hiredTs) {
		this.hiredTs = hiredTs;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public CompanyDetailsDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDetailsDto company) {
		this.company = company;
	}

}
