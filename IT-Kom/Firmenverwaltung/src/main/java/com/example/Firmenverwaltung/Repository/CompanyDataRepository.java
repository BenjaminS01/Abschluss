package com.example.Firmenverwaltung.Repository;

import com.example.Firmenverwaltung.Model.CompanyData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyDataRepository extends CrudRepository<CompanyData, Long> {
    List<CompanyData> findBySubject(String id);

    List<CompanyData> findAll();
}
