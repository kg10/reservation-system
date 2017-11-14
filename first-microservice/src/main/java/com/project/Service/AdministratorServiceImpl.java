package com.project.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Model.Client;
import com.project.Model.Personnel;
import com.project.Model.ServiceWrapper;
import com.project.Model.TimeTable;
import com.project.Model.Rest.TimeTableRequest;
import com.project.Repository.ClientRepository;
import com.project.Repository.PersonnelRepository;
import com.project.Repository.ServiceRepository;
import com.project.Repository.TimeTableRepository;

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

	@Override
	public void addPersonnelAndService(ServiceWrapper services) {
		for (com.project.Model.Service s : services.getServices())
			serviceRepository.saveAndFlush(s);
	}

	@Override
	public void addTimeTableToPerson(TimeTableRequest timeTableRequest) {
		Personnel personnel = personnelRepository.findOneByLastName(timeTableRequest.getLastName());
		List<TimeTable> timeTable = new ArrayList<>();
		timeTable = timeTableRequest.getTimeTable();
		for (TimeTable t : timeTable)
			t.setPersonnel(personnel);
		timeTableRepository.save(timeTable);
	}

	@Override
	public void createClient(Client client) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		clientRepository.save(client);
	}

	@Override
	public void disableClient(Long id) {
		Client client = clientRepository.findById(id);
		client.setActive(false);
		clientRepository.save(client);
	}
	

}
