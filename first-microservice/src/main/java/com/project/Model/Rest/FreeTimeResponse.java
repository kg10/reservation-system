package com.project.Model.Rest;

import java.sql.Time;

public class FreeTimeResponse {
	private Time timeFrom;
	private Time timeTo;

	public FreeTimeResponse() {
	}

	public FreeTimeResponse(Time timeFrom, Time timeTo) {
		super();
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
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

}
