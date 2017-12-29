package com.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Model.Personnel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Personnel findOneByLastName(String lastName);

    List<Personnel> findByActiveTrueAndService_Id(Long id);

    List<Personnel> findByActiveTrue();

    Personnel findById(Long id);

    @Transactional
    Long deleteById (Long id);

}
