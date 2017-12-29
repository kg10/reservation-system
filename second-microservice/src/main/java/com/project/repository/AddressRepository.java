package com.project.repository;

import com.project.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository  extends CrudRepository<Address, Long> {
    Address findById(Long id);
    Address findByPersonnelId(Long id);
    List<Address> findAll();
}
