package com.project.service;

import com.project.model.Address;
import com.project.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements  AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddress(Long id) {
       return addressRepository.findByPersonnelId(id);
    }

    @Override
    public void createNewAddress(Address address){
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(Long id, Address address) {
        Address addressToUpdate = addressRepository.findById(id);
        addressToUpdate.setCity(address.getCity());
        addressToUpdate.setNumberStreet(address.getNumberStreet());
        addressToUpdate.setPostalCode(address.getPostalCode());
        addressToUpdate.setStreet(address.getStreet());
        addressRepository.save(addressToUpdate);
    }
}
