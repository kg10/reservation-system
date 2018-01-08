package com.project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAll();

    Client findByLogin(String login);

    List<Client> findByFirstName(String firstname);

    Client findOneByLogin(String login);

    Client findById(Long id);
}
