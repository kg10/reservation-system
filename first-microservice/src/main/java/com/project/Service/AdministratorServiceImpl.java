package com.project.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.project.Exception.AdministratorException;
import com.project.Model.*;
import com.project.Model.Rest.HistoryResponse;
import com.project.Model.Rest.TimeTableResponse;
import com.project.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Model.Rest.TimeTableRequest;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private TimeTableRepository timeTableRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void addPersonnelAndService(ServiceWrapper services) throws AdministratorException {
        try {
            for (com.project.Model.Service s : services.getServices())
                serviceRepository.saveAndFlush(s);
        } catch (Exception e) {
            throw new AdministratorException("Invalid add", e);
        }
    }

    @Override
    public Long addPersonnel(Personnel person) throws AdministratorException {
        try {
            Personnel p = personnelRepository.saveAndFlush(person);
            return p.getId();
        } catch (Exception e) {
            throw new AdministratorException("Invalid add personel", e);
        }
    }

    @Override
    public void assignPersonToService(Long idPerson, List<Long> listIdService) throws AdministratorException {
        try {
            Set<Personnel> set = new HashSet<>();
            Personnel person = personnelRepository.findById(idPerson);
            set.add(person);

            for (Long id : listIdService) {
                com.project.Model.Service service = serviceRepository.findOneById(id);
                service.setPersonnel(set);
                serviceRepository.saveAndFlush(service);
            }
        } catch (Exception e) {
            throw new AdministratorException("Invalid assign", e);
        }
    }

    @Override
    public void assignPersonToService2(Long idPerson, List<String> listService) throws AdministratorException {
        try {
            Set<com.project.Model.Service> set = new HashSet<>();
            Personnel person = personnelRepository.findById(idPerson);

            for (String name : listService) {
                com.project.Model.Service service = serviceRepository.findOneByDescriptionService(name);
                set.add(service);
            }
            person.setService(set);
            personnelRepository.saveAndFlush(person);
        } catch (Exception e) {
            throw new AdministratorException("Invalid assign", e);
        }
    }

    @Override
    public void addTimeTableToPerson(TimeTableRequest timeTableRequest) throws AdministratorException {
        try {
            Personnel personnel = personnelRepository.findById(timeTableRequest.getIdPerson());
            List<TimeTable> timeTable = new ArrayList<>();
            timeTable = timeTableRequest.getTimeTable();
            for (TimeTable t : timeTable)
                t.setPersonnel(personnel);
            timeTableRepository.save(timeTable);
        } catch (Exception e) {
            throw new AdministratorException("Invalid add time to person", e);
        }
    }

    @Override
    public void createClient(Client client) throws AdministratorException {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            clientRepository.save(client);
        } catch (Exception e) {
            throw new AdministratorException("Invalid create client", e);
        }
    }

    @Override
    public void disableClient(Long id) throws AdministratorException {
        try {
            Client client = clientRepository.findById(id);
            client.setActive(false);
            clientRepository.save(client);
        } catch (Exception e) {
            throw new AdministratorException("Invalid disable client", e);
        }
    }

    @Override
    public void setServiceById(Long id, com.project.Model.Service service) throws AdministratorException {
        try {
            com.project.Model.Service s = serviceRepository.findOneById(id);
            s.setDescriptionService(service.getDescriptionService());
            s.setDuration(service.getDuration());
            s.setPrice(service.getPrice());
            serviceRepository.save(s);
        } catch (Exception e) {
            throw new AdministratorException("Invalid edit service", e);
        }
    }

    @Override
    public void setPersonnelById(Long id, Personnel personnel) throws AdministratorException {
        try {
            Personnel p = personnelRepository.findById(id);
            //p.setId(personnel.getId());
            p.setFirstName(personnel.getFirstName());
            p.setLastName(personnel.getLastName());
            p.setDescriptionPerson(personnel.getDescriptionPerson());
            personnelRepository.save(p);
        } catch (Exception e) {
            throw new AdministratorException("Invalid edit person", e);
        }
    }

    @Override
    public void disablePersonnel(Long id) throws AdministratorException {
        try {
            Personnel personnel = personnelRepository.findById(id);
            personnel.setActive(false);
            personnelRepository.save(personnel);
        } catch (Exception e) {
            throw new AdministratorException("Invalid disable person", e);
        }
    }

    @Override
    public void deletePersonnel(Long id) throws AdministratorException {
        try {
            personnelRepository.deleteById(id);
        } catch (Exception e) {
            throw new AdministratorException("Invalid delete person", e);
        }
    }

    @Override
    public List<TimeTableResponse> getTimeByPersonelId(Long id) throws AdministratorException {
        try {
            List<TimeTable> time = timeTableRepository.findByPersonnel_Id(id);
            List<TimeTableResponse> response = new ArrayList<>();
            for (TimeTable t : time) {
                response.add(new TimeTableResponse(t.getDay(), t.getTimeFrom(), t.getTimeTo()));
            }
            return response;
        } catch (Exception e) {
            throw new AdministratorException("Invalid get time personnel`s", e);
        }
    }

    @Override
    public void deleteTimeTable(Long idPerson) throws AdministratorException {
        try {
            timeTableRepository.deleteByPersonnelId(idPerson);
        } catch (Exception e) {
            throw new AdministratorException("Invalid delete time table", e);
        }
    }

    @Override
    public List<HistoryResponse> getAllReservation() throws AdministratorException {
        try {
            List<Reservation> reservationList = reservationRepository.findAll();
            List<HistoryResponse> historyList = new ArrayList<>();
            for (Reservation r : reservationList) {
                HistoryResponse history = new HistoryResponse();
                history.setId(r.getId());
                history.setDate(r.getDate());
                history.setPersonnel(r.getPersonnel().getFirstName() + " " + r.getPersonnel().getLastName());
                history.setClient(r.getClient().getFirstName() + " " + r.getClient().getLastName());
                history.setService(r.getService().getDescriptionService());
                history.setStatus(r.getStatus());
                history.setTimeFrom(r.getTimeFrom());
                history.setTimeTo(r.getTimeTo());
                historyList.add(history);
            }
            return historyList;
        } catch (Exception e) {
            throw new AdministratorException("Invalid get reservations", e);
        }
    }
}
