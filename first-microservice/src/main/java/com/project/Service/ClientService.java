package com.project.Service;

import com.project.Exception.ClientException;
import com.project.Model.*;
import com.project.Model.Rest.ClientResponse;
import com.project.Model.Rest.FreeTimeResponse;
import com.project.Model.Rest.HistoryReservation;
import com.project.Model.Rest.ReservationRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ClientService {
    List<Service> findServices(Long id) throws ClientException;

    List<Personnel> findPersonnels(Long id) throws ClientException;

    TimeTable findTimeTable(Long id, Date date) throws ClientException;

    Boolean addReservation(ReservationRequest reservationRequest) throws ParseException, ClientException;

    void disableReservation(String serviceName, Long id, Date date) throws ClientException;

    List<String> checkFreeTime(Date date, Long id, Long idService) throws ClientException;

    List<Personnel> findAllPersonnel() throws ClientException;

    List<Service> findAllServices() throws ClientException;

    Long createNewAccount(Client client) throws ClientException;

    Client findOneByLogin(String login) throws ClientException;

    List<HistoryReservation> getAllReservationByLogin(String login) throws ClientException;

    void disableReserv(Long id, Boolean status) throws ClientException;

    ClientResponse findClient(String login) throws ClientException;

    void updateClient(Long id, Client client) throws ClientException;

}
