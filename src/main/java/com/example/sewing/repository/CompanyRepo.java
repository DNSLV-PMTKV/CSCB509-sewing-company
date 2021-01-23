package com.example.sewing.repository;

import com.example.sewing.entity.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {

}
