package com.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Model.Personnel;
import org.springframework.transaction.annotation.Transactional;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Personnel findOneByLastName(String lastName);

    List<Personnel> findByService_Id(Long id);

    List<Personnel> findAll();

    Personnel findById(Long id);

    @Transactional
    Long deleteById (Long id);

}
