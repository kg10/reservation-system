package com.project.controller;

import com.project.model.Address;
import com.project.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/getAddressById/{id}")
    public ResponseEntity<Address> getTimeByPerson(@PathVariable Long id) {
        try {
            return new ResponseEntity<Address>(addressService.getAddress(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllAddress")
    public ResponseEntity<List<Address>> getTimeByPerson() {
        try {
            return new ResponseEntity<List<Address>>(addressService.getAllAddress(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Address>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addAddress", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addAddress(@RequestBody Address address) {
        try {
            addressService.createNewAddress(address);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateAddress/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        try {
            addressService.updateAddress(id, address);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
