package com.gmail.wojtass.michal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.gmail.wojtass.michal","com.gmail.wojtass.michal.components"})
@EnableScheduling
public class TazzBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(TazzBankApplication.class, args);


	}
}
