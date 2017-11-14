package com.project.Model;

import java.util.List;

public class ServiceWrapper {
	private List<Service> services;

	public ServiceWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	public ServiceWrapper(List<Service> services) {
		super();
		this.services = services;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	
}
