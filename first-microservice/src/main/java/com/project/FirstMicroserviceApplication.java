package com.project;

import com.project.Service.AddressClient;
import feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FirstMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstMicroserviceApplication.class, args);


//		String url = "http://localhost:8080/getAddress";
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

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/res/**").allowedOrigins("http://localhost:8072");
			}
		};
	}


}
