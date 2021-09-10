package com.example.Firmenverwaltung.Repository;

import com.example.Firmenverwaltung.Model.CompanyData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyDataRepository extends CrudRepository<CompanyData, Long> {
    CompanyDataRepository findById(long id);
    List<CompanyData> findAll();
}
