package com.project.Model.Rest;

import java.util.List;

import com.project.Model.TimeTable;

public class TimeTableRequest {
	private Long idPerson;
	private List<TimeTable> timeTable;

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public List<TimeTable> getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(List<TimeTable> timeTable) {
		this.timeTable = timeTable;
	}

}
