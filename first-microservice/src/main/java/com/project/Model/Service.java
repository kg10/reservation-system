package com.project.Model;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Service {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String descriptionService;
    private Time duration;
    private Integer price;
    @JsonProperty(access = Access.AUTO)
    @ManyToMany(mappedBy = "service", cascade = {CascadeType.ALL})
    private Set<Personnel> personnel = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "service")
    private Set<Reservation> reservations = new HashSet<>();

    public Service() {
        // TODO Auto-generated constructor stub
    }

    public Service(Long id, String descriptionService, Time duration, Integer price, Set<Personnel> personnel,
                   Set<Reservation> reservations) {
        super();
        this.id = id;
        this.descriptionService = descriptionService;
        this.duration = duration;
        this.price = price;
        this.personnel = personnel;
        this.reservations = reservations;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Set<Personnel> getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Set<Personnel> personnel) {
        this.personnel = personnel;
    }

}
