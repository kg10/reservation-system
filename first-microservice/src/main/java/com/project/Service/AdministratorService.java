package com.project.Service;

import com.project.Exception.AdministratorException;
import com.project.Model.*;
import com.project.Model.Rest.HistoryResponse;
import com.project.Model.Rest.TimeTableRequest;
import com.project.Model.Rest.TimeTableResponse;

import java.util.List;

public interface AdministratorService {
    void addPersonnelAndService(ServiceWrapper service) throws AdministratorException;

    void addTimeTableToPerson(TimeTableRequest timeTableRequest) throws AdministratorException;

    void createClient(Client client) throws AdministratorException;

    void disableClient(Long id) throws AdministratorException;

    void disablePersonnel(Long id) throws AdministratorException;

    void setServiceById(Long id, Service service) throws AdministratorException;

    void setPersonnelById(Long id, Personnel personnel) throws AdministratorException;

    void assignPersonToService(Long idPerson, List<Long> listIdService) throws AdministratorException;

    void assignPersonToService2(Long idPerson, List<String> listService) throws AdministratorException;

    Long addPersonnel(Personnel person) throws AdministratorException;

    void deletePersonnel(Long id) throws AdministratorException;

    List<TimeTableResponse> getTimeByPersonelId(Long id) throws AdministratorException;

    void deleteTimeTable(Long idPerson) throws AdministratorException;

    List<HistoryResponse> getAllReservation() throws AdministratorException;
}
