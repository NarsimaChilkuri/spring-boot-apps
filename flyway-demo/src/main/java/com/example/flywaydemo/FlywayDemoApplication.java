package com.example.flywaydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@SpringBootApplication
public class FlywayDemoApplication {
        
	@RequestMapping("/")
        String home() {
            return "Hello World - v3!";
        } 
	public static void main(String[] args) {
		SpringApplication.run(FlywayDemoApplication.class, args);
	}

}
