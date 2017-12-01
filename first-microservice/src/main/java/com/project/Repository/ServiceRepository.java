package com.project.Repository;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	List<Service> findByPersonnel_Id(Long id);

	List<Service> findAll();

	Service findOneByDescriptionService(String descriptionService);

	Service findByDescriptionService(String descriptionService);

	Service findOneById(Long id);

}
