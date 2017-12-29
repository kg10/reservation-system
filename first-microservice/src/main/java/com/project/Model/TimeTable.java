package com.project.Model;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "day", "personnel_id" }))
@Entity
public class TimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// @Column(unique=true)
	private Integer day;
	private Time timeFrom;
	private Time timeTo;
//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "personnel_id")
	private Personnel personnel;

	public TimeTable() {
		// TODO Auto-generated constructor stub
	}

	public TimeTable(Long id, Integer day, Time timeFrom, Time timeTo, Personnel personnel) {
		super();
		this.id = id;
		this.day = day;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.personnel = personnel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
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

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	@Override
	public String toString() {
		return "TimeTable [id=" + id + ", day=" + day + ", timeFrom=" + timeFrom + ", timeTo=" + timeTo + ", personnel="
				+ personnel + "]";
	}

}