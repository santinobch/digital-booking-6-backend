package com.example.DigitalBookingBEG6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class DigitalBookingBeG6Application {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBookingBeG6Application.class, args);
	}
}
