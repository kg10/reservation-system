package com.project.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
	public List<Client> findAll();
	public Client findByLogin(String login);
	public List<Client> findByFirstName(String firstname);
	public Client findById(Long id);
	
}
