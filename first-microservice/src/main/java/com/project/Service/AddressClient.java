package com.project.Service;

import com.project.Model.Address;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("client")
public interface AddressClient {

    @GetMapping("/getAllAddress")
    List<Address> getAllAddress();

    @GetMapping("/getAddressById/{id}")
    Address getAddress(@PathVariable("id") Long id);

    @PostMapping("/addAddress")
    void addAddress(@RequestBody Address address);

    @PutMapping("updateAddress/{id}")
    void updateAddress(@PathVariable("id") Long id, @RequestBody Address address);
}

