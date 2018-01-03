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
@Api(value="Second microservice", description="Operations to something else... ")
@CrossOrigin
public class FeignController {
//działało jak był podany url!!!

//    private static <T> T createClient(Class<T> type, String uri) {
//        return Feign.builder()
//                .client(new OkHttpClient())
//                .encoder(new GsonEncoder())
//                .decoder(new GsonDecoder())
//                .logger(new Slf4jLogger(type))
//                .logLevel(Logger.Level.FULL)
//                .target(type, uri);
//    }
//
//    @RequestMapping("/getAddress")
//    //@RequestLine("GET")
//    public ResponseEntity<List<Address>> getAddress(){
////        String url = "http://CLIENT/getAddress";
//        String url = "http://CLIENT/";
//        return new ResponseEntity<List<Address>>(createClient(AddressClient.class, url).findAddress(), HttpStatus.OK);
//    }
///////////////////////////////////////////
    @Autowired
    private AddressClient addressClient;

    @GetMapping("/getAllAddress")
    public ResponseEntity<List<Address>> getAddress(){
        try{
        return new ResponseEntity<List<Address>>(addressClient.getAllAddress(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Address>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAddress/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        try{
        return new ResponseEntity<Address>(addressClient.getAddress(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addAddress", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addAddress(@RequestBody Address address){
       try{
        addressClient.addAddress(address);
           return new ResponseEntity<Void>(HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping(value = "/updateAddress/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editAddress(@PathVariable Long id, @RequestBody Address address){
        try{
        addressClient.updateAddress(id, address);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
