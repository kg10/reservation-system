package com.project.Model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Reservation {
	@Id
	@GeneratedValue
	private Long id;
	private Time timeFrom;
	@JsonIgnore
	private Time timeTo;
	@JsonProperty(access = Access.AUTO)
	private Boolean status = true;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "personnel_id")
	private Personnel personnel;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
	@Temporal(TemporalType.DATE)
	private Date date;

	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public Reservation(Long id, Time timeFrom, Time timeTo, Boolean status, Personnel personnel, Service service,
			Date date) {
		super();
		this.id = id;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.status = status;
		this.personnel = personnel;
		this.service = service;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Time timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Time getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Time timeTo) {
		this.timeTo = timeTo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
