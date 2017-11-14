package com.project.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.project.Model.Personnel;
import com.project.Model.Service;
import com.project.Model.TimeTable;
import com.project.Model.Rest.FreeTimeResponse;
import com.project.Model.Rest.ReservationRequest;

public interface ClientService {
	public List<Service> findServices(String lastName);

	public List<Personnel> findPersonnels(String descriptionService);

	public TimeTable findTimeTable(String lastName, Date date);

	public Boolean addReservation(ReservationRequest reservationRequest) throws ParseException;

	public void disableReservation(String serviceName, Long id, Date date);

	public List<FreeTimeResponse> checkFreeTime(Date date, String lastName);
}
