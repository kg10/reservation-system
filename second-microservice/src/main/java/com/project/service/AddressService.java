package com.project.service;

import com.project.model.Address;

import java.util.List;

public interface AddressService {
    Address getAddress(Long id);
    List<Address> getAllAddress();
    void createNewAddress(Address address);
    void updateAddress(Long id, Address address);
}
