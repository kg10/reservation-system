package com.project.Model.Rest;

import java.sql.Time;
import java.util.Date;

public class HistoryResponse {
    private Long id;
    private Time timeFrom;
    private Time timeTo;
    private Boolean status;
    private String personnel;
    private String client;
    private String service;
    private Date date;

    public HistoryResponse() {
    }

    public HistoryResponse(Long id, Time timeFrom, Time timeTo, Boolean status, String personnel, String client, String service, Date date) {
        this.id = id;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.status = status;
        this.personnel = personnel;
        this.client = client;
        this.service = service;
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

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
