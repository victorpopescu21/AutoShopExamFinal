package ro.itschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableAutoConfiguration
//@EnableConfigurationProperties
public class AutoShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoShopApplication.class, args);
	}

}
