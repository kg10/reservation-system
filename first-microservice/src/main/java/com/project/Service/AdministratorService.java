package com.project.Service;

import com.project.Model.Client;
import com.project.Model.Personnel;
import com.project.Model.Service;
import com.project.Model.ServiceWrapper;
import com.project.Model.Rest.TimeTableRequest;

public interface AdministratorService {
	void addPersonnelAndService(ServiceWrapper service);

	void addTimeTableToPerson(TimeTableRequest timeTableRequest);

	void createClient(Client client);
	
	void disableClient(Long id);

	void disablePersonnel(Long id);

	void setServiceById(Long id, Service service);

	void setPersonnelById(Long id, Personnel personnel);
}
