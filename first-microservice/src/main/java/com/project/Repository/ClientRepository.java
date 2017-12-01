package com.project.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
	List<Client> findAll();
	Client findByLogin(String login);
	List<Client> findByFirstName(String firstname);
	Client findOneByLogin(String login);
	Client findById(Long id);
}
