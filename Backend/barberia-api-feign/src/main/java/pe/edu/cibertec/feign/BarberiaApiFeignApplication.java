package pe.edu.cibertec.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BarberiaApiFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarberiaApiFeignApplication.class, args);
	}

}
