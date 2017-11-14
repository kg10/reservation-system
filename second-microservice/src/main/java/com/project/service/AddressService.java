package com.project.service;

import com.project.model.Address;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    public List<Address> getAddress() {
        Address a = new Address(1L, "Pszczyna", "Wodzisławska", 5, "43-240");
        Address b = new Address(2L, "Pszczyna", "Wodzisławska", 5, "43-240");
        List<Address> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        return list;
    }
}
