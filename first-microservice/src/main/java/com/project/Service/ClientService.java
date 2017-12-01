package com.project.Service;

import com.project.Model.Client;
import com.project.Model.Personnel;
import com.project.Model.Rest.FreeTimeResponse;
import com.project.Model.Rest.HistoryReservation;
import com.project.Model.Rest.ReservationRequest;
import com.project.Model.Service;
import com.project.Model.TimeTable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ClientService {
	List<Service> findServices(Long id);

	List<Personnel> findPersonnels(Long id);

	TimeTable findTimeTable(Long id, Date date);

	Boolean addReservation(ReservationRequest reservationRequest) throws ParseException;

	void disableReservation(String serviceName, Long id, Date date);

	List<String> checkFreeTime(Date date, Long id,Long idService);

	List<Personnel> findAllPersonnel();

	List<Service> findAllServices();

	void createNewAccount(Client client);

	Client findOneByLogin(String login);

	List<HistoryReservation> getAllReservationByLogin(String login);

}
