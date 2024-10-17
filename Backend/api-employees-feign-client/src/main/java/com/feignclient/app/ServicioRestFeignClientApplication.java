package com.feignclient.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioRestFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioRestFeignClientApplication.class, args);
	}

}
