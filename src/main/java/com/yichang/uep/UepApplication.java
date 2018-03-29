package com.yichang.uep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class UepApplication {

	public static void main(String[] args) {
		SpringApplication.run(UepApplication.class, args);
	}
}
