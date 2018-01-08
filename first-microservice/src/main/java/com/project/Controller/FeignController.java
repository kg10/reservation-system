package com.project.Controller;


import com.project.Model.Address;
import com.project.Service.AddressClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value="Second microservice", description="Api with address")
@CrossOrigin
public class FeignController {
    @Autowired
    private AddressClient addressClient;

    @GetMapping("/getAllAddress")
    public ResponseEntity<List<Address>> getAddress(){
        try{
        return new ResponseEntity<>(addressClient.getAllAddress(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAddress/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        try{
        return new ResponseEntity<>(addressClient.getAddress(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addAddress", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addAddress(@RequestBody Address address){
       try{
        addressClient.addAddress(address);
           return new ResponseEntity<>(HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping(value = "/updateAddress/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editAddress(@PathVariable Long id, @RequestBody Address address){
        try{
        addressClient.updateAddress(id, address);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
