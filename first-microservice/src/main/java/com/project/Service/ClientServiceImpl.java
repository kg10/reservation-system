package com.project.Service;

import java.sql.Time;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.Model.*;
import com.project.Model.Rest.HistoryReservation;
import com.project.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.Model.Rest.FreeTimeResponse;
import com.project.Model.Rest.ReservationRequest;
import com.project.Utils.Convert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ClientServiceImpl implements ClientService {
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private PersonnelRepository personnelRepository;
	@Autowired
	private TimeTableRepository timeTableRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public List<Service> findServices(Long id){
		return serviceRepository.findByPersonnel_Id(id);
	}

	@Override
	public List<Personnel> findPersonnels(Long id) {
		return personnelRepository.findByService_Id(id);
	}

	@Override
	public List<Personnel> findAllPersonnel() {
		return personnelRepository.findAll();
	}

	@Override
	public List<Service> findAllServices() {
		return serviceRepository.findAll();
	}

	@Override
	public void createNewAccount(Client client) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		client.setRole("USER");
		clientRepository.save(client);
	}

	@Override
	public Client findOneByLogin(String login) {
		return clientRepository.findOneByLogin(login);
	}

	@Override
	public TimeTable findTimeTable(Long id, Date date) {
		return timeTableRepository.findByDayAndPersonnel_Id(Convert.convertDateToDay(date), id);
	}

	@Override
	public Boolean addReservation(ReservationRequest reservationRequest) throws ParseException {
		Personnel person = personnelRepository.findById(reservationRequest.getIdPersonnel());
		Service service = serviceRepository.findOneById(reservationRequest.getIdService());
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
	public List<String> checkFreeTime(Date date, Long id, Long idService) {
		TimeTable timeTable = timeTableRepository.findByDayAndPersonnel_Id(Convert.convertDateToDay(date),
				id);
		List<Reservation> reservations = reservationRepository.findByDateAndPersonnel_IdOrderByTimeFromAsc(date,
				id);
		Service service = serviceRepository.findOneById(idService);
		List<FreeTimeResponse> times = new ArrayList<>();
		Reservation tmp = new Reservation();
		int iter = 0;
		List<String> timeList = new ArrayList<>();
		LocalTime timeService = service.getDuration().toLocalTime();

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

		for(FreeTimeResponse f:times){
			LocalTime timeFrom = f.getTimeFrom().toLocalTime();
			LocalTime timeTo = f.getTimeTo().toLocalTime();
			Duration duration = Duration.between(timeFrom,timeTo);

			while(duration.getSeconds()>0){
				if((Duration.between(timeFrom.plusMinutes(timeService.getMinute()).plusHours(timeService.getHour()),timeTo)).getSeconds()<0)
					break;
				timeList.add(timeFrom.toString());
				timeFrom = timeFrom.plusMinutes(15L);
				duration = Duration.between(timeFrom,timeTo);
			}
		}

		for(String a: timeList){
			System.out.println(a);
		}

		return timeList;
	}

	@Override
	public List<HistoryReservation> getAllReservationByLogin(String login) {
		List<Reservation> reservationList = reservationRepository.findAllByClient_Login(login);
		List<HistoryReservation> historyList = new ArrayList<>();
		HistoryReservation history = new HistoryReservation();
		for(Reservation r : reservationList){
			history.setId(r.getId());
			history.setDate(r.getDate());
			history.setPersonnel(r.getPersonnel().getFirstName() + " " + r.getPersonnel().getLastName());
			history.setService(r.getService().getDescriptionService());
			history.setStatus(r.getStatus());
			history.setTimeFrom(r.getTimeFrom());
			history.setTimeTo(r.getTimeTo());
			historyList.add(history);
		}
		return historyList;
	}
}
