package com.project.Model.Rest;

import java.sql.Time;

public class TimeTableResponse {
    private Integer day;
    private Time timeFrom;
    private Time timeTo;

    public TimeTableResponse() {
    }

    public TimeTableResponse(Integer day, Time timeFrom, Time timeTo) {
        this.day = day;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
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
}
