package com.tga.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SearchgenericServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchgenericServiceApplication.class, args);
	}

}
