package com.example.Firmenverwaltung.Repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyData extends CrudRepository<CompanyData, Long> {
    CompanyData findById(long id);
    List<CompanyData> findAll();
}
