package com.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private Integer numberStreet;
    private String postalCode;
    private Long personnelId;
    public Address() {
    }

    public Address(Long id, String city, String street, Integer numberStreet, String postalCode, Long personnelId) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.numberStreet = numberStreet;
        this.postalCode = postalCode;
        this.personnelId = personnelId;
    }

//    public Address(String city, String street, Integer numberStreet, String postalCode, Long personnelId) {
//        this.city = city;
//        this.street = street;
//        this.numberStreet = numberStreet;
//        this.postalCode = postalCode;
//        this.personnelId = personnelId;
//    }

    public Long getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Long personnelId) {
        this.personnelId = personnelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberStreet() {
        return numberStreet;
    }

    public void setNumberStreet(Integer numberStreet) {
        this.numberStreet = numberStreet;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
