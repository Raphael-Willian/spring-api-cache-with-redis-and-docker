package com.raphael.apicache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ApicacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicacheApplication.class, args);
	}

}
