package com.project.Service;

import java.sql.Time;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.Exception.ClientException;
import com.project.Model.*;
import com.project.Model.Rest.ClientResponse;
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
    public List<Service> findServices(Long id) throws ClientException {
        try {
            return serviceRepository.findByPersonnel_Id(id);
        } catch (Exception e) {
            throw new ClientException("Invalid find service", e);
        }
    }

    @Override
    public List<Personnel> findPersonnels(Long id) throws ClientException {
        try {
            return personnelRepository.findByActiveTrueAndService_Id(id);
        } catch (Exception e) {
            throw new ClientException("Invalid find person", e);
        }
    }

    @Override
    public List<Personnel> findAllPersonnel() throws ClientException {
        try {
            return personnelRepository.findByActiveTrue();
        } catch (Exception e) {
            throw new ClientException("Invalid find persons", e);
        }
    }

    @Override
    public List<Service> findAllServices() throws ClientException {
        try {
            return serviceRepository.findAll();
        } catch (Exception e) {
            throw new ClientException("Invalid find services", e);
        }
    }

    @Override
    public Long createNewAccount(Client client) throws ClientException {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            client.setRole("USER");
            return clientRepository.saveAndFlush(client).getId();
        } catch (Exception e) {
            throw new ClientException("Invalid create account", e);
        }
    }

    @Override
    public Client findOneByLogin(String login) throws ClientException {
        try {
            return clientRepository.findOneByLogin(login);
        } catch (Exception e) {
            throw new ClientException("Invalid create account", e);
        }
    }

    @Override
    public ClientResponse findClient(String login) throws ClientException {
        try {
            Client client = clientRepository.findByLogin(login);
            ClientResponse response = new ClientResponse();
            response.setId(client.getId());
            response.setEmail(client.getEmail());
            response.setFirstName(client.getFirstName());
            response.setLastName(client.getLastName());
            return response;
        } catch (Exception e) {
            throw new ClientException("Invalid create account", e);
        }
    }

    @Override
    public TimeTable findTimeTable(Long id, Date date) throws ClientException {
        try {
            return timeTableRepository.findByDayAndPersonnel_Id(Convert.convertDateToDay(date), id);
        } catch (Exception e) {
            throw new ClientException("Invalid create account", e);
        }
    }

    @Override
    public Boolean addReservation(ReservationRequest reservationRequest) throws ParseException, ClientException {
        try {
            Client client = clientRepository.findByLogin(reservationRequest.getLoginClient());
            Personnel person = personnelRepository.findById(reservationRequest.getIdPersonnel());
            Service service = serviceRepository.findOneById(reservationRequest.getIdService());
            if (person == null || service == null)
                return false;

            Reservation reservation = reservationRequest.getReservation();
            List<String> listTime = checkFreeTime(reservation.getDate(), reservationRequest.getIdPersonnel(), reservationRequest.getIdService());

            reservation.setPersonnel(person);
            reservation.setService(service);
            reservation.setClient(client);
            LocalTime t1 = reservation.getTimeFrom().toLocalTime();
            LocalTime t2 = service.getDuration().toLocalTime();
            LocalTime result = t1.plusHours(t2.getHour()).plusMinutes(t2.getMinute());
            reservation.setTimeTo(Time.valueOf(result));

            if (!listTime.contains(t1.toString().substring(0, 5))) {
                return false;
            }

            reservationRepository.save(reservation);
            return true;
        } catch (Exception e) {
            throw new ClientException("Invalid create reservation", e);
        }
    }

    @Override
    public void disableReservation(String descriptionService, Long id, Date date) throws ClientException {
        try {
            Service serviceId = serviceRepository.findByDescriptionService(descriptionService);
            Reservation reservation = reservationRepository.findOneByServiceAndDate(serviceId, date);
            reservation.setStatus(false);
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new ClientException("Invalid disable reservation", e);
        }

    }

    @Override
    public List<String> checkFreeTime(Date date, Long id, Long idService) throws ClientException {
        try {
            TimeTable timeTable = timeTableRepository.findByDayAndPersonnel_Id(Convert.convertDateToDay(date),
                    id);
            List<Reservation> reservations = reservationRepository.findByDateAndStatusTrueAndPersonnel_IdOrderByTimeFromAsc(date,
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

            for (FreeTimeResponse f : times) {
                LocalTime timeFrom = f.getTimeFrom().toLocalTime();
                LocalTime timeTo = f.getTimeTo().toLocalTime();
                Duration duration = Duration.between(timeFrom, timeTo);

                while (duration.getSeconds() > 0) {
                    if ((Duration.between(timeFrom.plusMinutes(timeService.getMinute()).plusHours(timeService.getHour()), timeTo)).getSeconds() < 0)
                        break;
                    timeList.add(timeFrom.toString());
                    timeFrom = timeFrom.plusMinutes(15L);
                    duration = Duration.between(timeFrom, timeTo);
                }
            }

            for (String a : timeList) {
                System.out.println(a);
            }

            return timeList;
        } catch (Exception e) {
            throw new ClientException("Invalid check free time", e);
        }
    }

    @Override
    public List<HistoryReservation> getAllReservationByLogin(String login) throws ClientException {
        try {
            List<Reservation> reservationList = reservationRepository.findAllByClient_Login(login);
            List<HistoryReservation> historyList = new ArrayList<>();
            for (Reservation r : reservationList) {
                HistoryReservation history = new HistoryReservation();
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
        } catch (Exception e) {
            throw new ClientException("Invalid get all reservations", e);
        }
    }

    @Override
    public void disableReserv(Long id, Boolean status) throws ClientException {
        try {
            Reservation reservation = reservationRepository.findOneById(id);
            reservation.setStatus(status);
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new ClientException("Invalid disable reservation", e);
        }
    }

    @Override
    public void updateClient(Long id, Client client) throws ClientException {
        try {
            Client clientToUpdate = clientRepository.findById(id);
            clientToUpdate.setFirstName(client.getFirstName());
            clientToUpdate.setLastName(client.getLastName());
            clientToUpdate.setEmail(client.getEmail());
            clientRepository.save(clientToUpdate);
        } catch (Exception e) {
            throw new ClientException("Invalid update client", e);
        }
    }
}
