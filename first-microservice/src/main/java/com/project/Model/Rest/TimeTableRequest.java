package com.project.Model.Rest;

import java.util.List;

import com.project.Model.TimeTable;

public class TimeTableRequest {
	private String lastName;
	private List<TimeTable> timeTable;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<TimeTable> getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(List<TimeTable> timeTable) {
		this.timeTable = timeTable;
	}

}
