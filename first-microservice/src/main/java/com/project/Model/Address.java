package com.project.Model;

public class Address {
    private Long id;
    private String city;
    private String street;
    private Integer numberStreet;
    private String postalCode;

    public Address() {
    }

    public Address(Long id, String city, String street, Integer numberStreet, String postalCode) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.numberStreet = numberStreet;
        this.postalCode = postalCode;
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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberStreet=" + numberStreet +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
