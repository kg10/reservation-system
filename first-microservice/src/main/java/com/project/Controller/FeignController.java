package com.project.Controller;


//import com.project.Service.AddressClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@Api(value="Second microservice", description="Operations to something else... ")
//public class FeignController {
////działało jak był podany url!!!
//
////    private static <T> T createClient(Class<T> type, String uri) {
////        return Feign.builder()
////                .client(new OkHttpClient())
////                .encoder(new GsonEncoder())
////                .decoder(new GsonDecoder())
////                .logger(new Slf4jLogger(type))
////                .logLevel(Logger.Level.FULL)
////                .target(type, uri);
////    }
////
////    @RequestMapping("/getAddress")
////    //@RequestLine("GET")
////    public ResponseEntity<List<Address>> getAddress(){
//////        String url = "http://CLIENT/getAddress";
////        String url = "http://CLIENT/";
////        return new ResponseEntity<List<Address>>(createClient(AddressClient.class, url).findAddress(), HttpStatus.OK);
////    }
/////////////////////////////////////////////
//    @Autowired
//    private AddressClient addressClient;
//
//    @GetMapping("/test")
//    public String getAddress(){
//        return addressClient.getAddress().get(1).toString();
//    }
//}
