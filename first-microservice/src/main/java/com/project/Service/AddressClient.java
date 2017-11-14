package com.project.Service;

import com.project.Model.Address;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("client")
public interface AddressClient {

    @RequestMapping("/getAddress")
    public List<Address> getAddress();

//    @RequestLine("POST /")
//    @Headers("Content-Type: application/json")
//    Address createAddress(Address address);
}
