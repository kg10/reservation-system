package com.project.Service;

import java.sql.Time;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.Model.Personnel;
import com.project.Model.Reservation;
import com.project.Model.Service;
import com.project.Model.TimeTable;
import com.project.Model.Rest.FreeTimeResponse;
import com.project.Model.Rest.ReservationRequest;
import com.project.Repository.PersonnelRepository;
import com.project.Repository.ReservationRepository;
import com.project.Repository.ServiceRepository;
import com.project.Repository.TimeTableRepository;
import com.project.Utils.Convert;

public class ClientServiceImpl implements ClientService {
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private PersonnelRepository personnelRepository;
	@Autowired
	private TimeTableRepository timeTableRepository;
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public List<Service> findServices(String lastName) {
		return serviceRepository.findByPersonnel_LastName(lastName);
	}

	@Override
	public List<Personnel> findPersonnels(String descriptionService) {
		return personnelRepository.findByService_DescriptionService(descriptionService);
	}

	@Override
	public TimeTable findTimeTable(String lastName, Date date) {
		return timeTableRepository.findByDayAndPersonnel_LastName(Convert.convertDateToDay(date), lastName);
	}

	@Override
	public Boolean addReservation(ReservationRequest reservationRequest) throws ParseException {
		Personnel person = personnelRepository.findOneByLastName(reservationRequest.getLastName());
		Service service = serviceRepository.findOneByDescriptionService(reservationRequest.getServiceName());
		if (person == null || service == null)
			return false;
		Reservation reservation = reservationRequest.getReservation();
		reservation.setPersonnel(person);
		reservation.setService(service);
		LocalTime t1 = reservation.getTimeFrom().toLocalTime();
		LocalTime t2 = service.getDuration().toLocalTime();
		LocalTime result = t1.plusHours(t2.getHour()).plusMinutes(t2.getMinute());
		reservation.setTimeTo(Time.valueOf(result));

		reservationRepository.save(reservation);
		return true;
	}

	@Override
	public void disableReservation(String descriptionService, Long id, Date date) {
		Service serviceId = serviceRepository.findByDescriptionService(descriptionService);
		Reservation reservation = reservationRepository.findOneByServiceAndDate(serviceId, date); // tutaj trzeba
																									// dołożyć jeszcze
																									// login klienta
		reservation.setStatus(false);
		reservationRepository.save(reservation);

	}

	@Override
	public List<FreeTimeResponse> checkFreeTime(Date date, String lastName) {
		TimeTable timeTable = timeTableRepository.findByDayAndPersonnel_LastName(Convert.convertDateToDay(date),
				lastName);
		List<Reservation> reservations = reservationRepository.findByDateAndPersonnel_LastNameOrderByTimeFromAsc(date,
				lastName);
		List<FreeTimeResponse> times = new ArrayList<>();
		Reservation tmp = new Reservation();
		int iter = 0;

		if (reservations.size() != 0) {
			for (Reservation r : reservations) {
				if (iter == 0)
					times.add(new FreeTimeResponse(timeTable.getTimeFrom(), r.getTimeFrom()));
				else {
					if (iter != reservations.size())
						times.add(new FreeTimeResponse(tmp.getTimeTo(), r.getTimeFrom()));
				}
				tmp = r;
				++iter;
			}
			times.add(new FreeTimeResponse(tmp.getTimeTo(), timeTable.getTimeTo()));
		} else
			times.add(new FreeTimeResponse(timeTable.getTimeFrom(), timeTable.getTimeTo()));
		return times;
	}

}
