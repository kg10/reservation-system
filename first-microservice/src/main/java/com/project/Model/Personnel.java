package com.project.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Personnel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	@Column(unique = true)
	private String lastName;
	private String descriptionPerson;
	@JsonProperty(access = Access.AUTO)
	private Boolean active = true;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "personnel", cascade = { CascadeType.ALL })
	private Set<Service> service = new HashSet<>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "personnel")
	private List<TimeTable> timeTable;

	@JsonIgnore
	@OneToMany(mappedBy = "personnel")
	private Set<Reservation> reservations;

	public Personnel() {
	}

	public Personnel(Long id, String firstName, String lastName, String descriptionPerson, Set<Service> service,
			Set<Reservation> reservations, List<TimeTable> timeTable, Boolean active) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.descriptionPerson = descriptionPerson;
		this.service = service;
		this.reservations = reservations;
		this.timeTable = timeTable;
		this.active = active;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<TimeTable> getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(List<TimeTable> timeTable) {
		this.timeTable = timeTable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescriptionPerson() {
		return descriptionPerson;
	}

	public void setDescriptionPerson(String descriptionPerson) {
		this.descriptionPerson = descriptionPerson;
	}

	public Set<Service> getService() {
		return service;
	}

	public void setService(Set<Service> service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "Personnel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", descriptionPerson="
				+ descriptionPerson + ", service=" + service + ", timeTable=" + timeTable + ", reservations="
				+ reservations + "]";
	}

}
