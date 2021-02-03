package com.example.sewing.service;

import java.time.Instant;
import java.util.Optional;

import com.example.sewing.dto.CompanyDetailsDto;
import com.example.sewing.entity.Company;
import com.example.sewing.exceptions.DoesNotExistsException;
import com.example.sewing.exceptions.InvalidInputException;
import com.example.sewing.repository.CompanyRepo;
import com.example.sewing.tools.ObjectConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
	@Autowired
	private CompanyRepo repository;

	public CompanyDetailsDto createCompany(CompanyDetailsDto dto) {
		if (repository.existsByName(dto.getName())) {
			throw new InvalidInputException(String.format("Company with name '%s' already exists.", dto.getName()));
		}
		Company company = ObjectConverter.convertObject(dto, Company.class);
		Company saved = repository.save(company);
		return ObjectConverter.convertObject(saved, CompanyDetailsDto.class);
	}

	public CompanyDetailsDto getCompany(Long id) {
		Optional<Company> company = repository.findById(id);
		if (!company.isPresent()) {
			throw new DoesNotExistsException(String.format("Company with ID: %d does not exist.", id));
		}
		return ObjectConverter.convertObject(company.get(), CompanyDetailsDto.class);
	}

	public CompanyDetailsDto updateCompany(CompanyDetailsDto update, Long id) {
		Optional<Company> existing = repository.findById(id);
		if (!existing.isPresent()) {
			throw new DoesNotExistsException(String.format("Company with ID: %d does not exist.", id));
		}
		if (update.getName() != existing.get().getName() && repository.existsByName(update.getName())) {
			throw new InvalidInputException(String.format("Company with name '%s' already exists.", update.getName()));
		}
		existing.get().setName(update.getName());
		existing.get().setUpdatedTs(Instant.now());
		return ObjectConverter.convertObject(repository.save(existing.get()), CompanyDetailsDto.class);
	}

}
