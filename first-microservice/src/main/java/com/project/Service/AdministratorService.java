package com.project.Service;

import com.project.Model.Client;
import com.project.Model.ServiceWrapper;
import com.project.Model.Rest.TimeTableRequest;

public interface AdministratorService {
	public void addPersonnelAndService(ServiceWrapper service);

	public void addTimeTableToPerson(TimeTableRequest timeTableRequest);

	public void createClient(Client client);
	
	public void disableClient(Long id);

}
