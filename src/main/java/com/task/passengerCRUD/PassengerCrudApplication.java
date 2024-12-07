package com.task.passengerCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Passenger REST API", description = "The Passenger CRUD API allows users to perform Create, Read, Update, and Delete operations on passenger data. It supports managing passenger details like name, gender, mobile, and travel destinations, with ticket generation included during updates.Rest api which has endpoints for performing crud operation on Passenger table",version = "1.0.0",contact = @Contact(name = "Nithyashree",email = "nithyavenkatesh6112@gmail.com",url = "https://github.com/Nithya-Shree-V")))
public class PassengerCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassengerCrudApplication.class, args);
	}

}
