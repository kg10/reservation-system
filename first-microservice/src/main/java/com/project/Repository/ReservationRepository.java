package com.project.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.Model.Reservation;
import com.project.Model.Service;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	public List<Reservation> findAll();

	public List<Reservation> findByPersonnel_LastName(String lastName);

	public Reservation findOneByServiceAndDate(Service service, Date date);

	public List<Reservation> findByDateAndPersonnel_LastNameOrderByTimeFromAsc(Date date, String lastName);
}
