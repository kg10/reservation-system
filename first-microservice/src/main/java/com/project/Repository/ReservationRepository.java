package com.project.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.Model.Reservation;
import com.project.Model.Service;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	List<Reservation> findAll();

	List<Reservation> findByPersonnel_LastName(String lastName);

	Reservation findOneByServiceAndDate(Service service, Date date);

	List<Reservation> findByDateAndPersonnel_IdOrderByTimeFromAsc(Date date, Long id);

	List<Reservation> findAllByClient_Login(String login);
}
