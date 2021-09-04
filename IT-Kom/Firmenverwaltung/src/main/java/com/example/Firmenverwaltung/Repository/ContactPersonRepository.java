package com.example.Firmenverwaltung.Repository;

import com.example.Firmenverwaltung.Model.ContactPerson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactPersonRepository extends CrudRepository<ContactPerson, Long> {

    ContactPerson findById(long id);
    List<ContactPerson> findAll();

}
