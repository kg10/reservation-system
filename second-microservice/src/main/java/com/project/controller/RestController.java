package com.project.controller;

import com.project.model.Address;
import com.project.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
//@RequestMapping("")
public class RestController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAddress")
    public List<Address> getAddress() {
        return addressService.getAddress();
    }
}
