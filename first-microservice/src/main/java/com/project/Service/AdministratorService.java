package com.project.Service;

import com.project.Model.*;
import com.project.Model.Rest.HistoryResponse;
import com.project.Model.Rest.TimeTableRequest;
import com.project.Model.Rest.TimeTableResponse;

import java.util.List;

public interface AdministratorService {
	void addPersonnelAndService(ServiceWrapper service);

	void addTimeTableToPerson(TimeTableRequest timeTableRequest);

	void createClient(Client client);
	
	void disableClient(Long id);

	void disablePersonnel(Long id);

	void setServiceById(Long id, Service service);

	void setPersonnelById(Long id, Personnel personnel);

	void assignPersonToService(Long idPerson, List<Long> listIdService);

	void assignPersonToService2(Long idPerson, List<String> listService);

	Long addPersonnel(Personnel person);

	void deletePersonnel(Long id);

	List<TimeTableResponse> getTimeByPersonelId(Long id);

	void deleteTimeTable(Long idPerson);

	List<HistoryResponse> getAllReservation();
}
