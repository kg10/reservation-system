package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients
public class FirstMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstMicroserviceApplication.class, args);


		//String url = "http://localhost:8080/getAddress";
//
//		AddressClient client = Feign.builder()
//				.encoder(new GsonEncoder())
//				.decoder(new GsonDecoder())
//				.requestInterceptor(System.out::println) 	// show data to send to client
//				.logLevel(Logger.Level.FULL)
//				.target(AddressClient.class, url);
//
//		List<Address> all = client.findAddress();
//		all.forEach(System.out::println);

//		System.out.println(all);


		String password = "abc";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		System.out.println(hashedPassword);
	}


}
