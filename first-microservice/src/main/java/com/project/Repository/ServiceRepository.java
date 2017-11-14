package com.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	public List<Service> findByPersonnel_LastName(String lastName);

	public List<Service> findAll();

	public Service findOneByDescriptionService(String descriptionService);

	public Service findByDescriptionService(String descriptionService);

}
