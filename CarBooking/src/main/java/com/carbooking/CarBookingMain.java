package com.carbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.carbooking.controllers"})
public class CarBookingMain {
	public static void main(String[] args) {
		SpringApplication.run(CarBookingMain.class, args);
	}
}