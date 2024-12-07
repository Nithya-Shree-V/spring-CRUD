package com.task.passengerCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task.passengerCRUD.dto.Passenger;
import com.task.passengerCRUD.service.PassengerService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class PassengerController {
	@Autowired
	PassengerService service;
	
	@PostMapping("/passengers")
	@Operation(summary = "Adding One passenger Record")
	public ResponseEntity<Object> savePassenger(@RequestBody Passenger passenger) {
		return service.save(passenger);
	}
	
	@PostMapping("/passengers/multiple")
	@Operation(summary = "Adding Multiple Passenger Record")
	public ResponseEntity<Object> savePassengers(@RequestBody List<Passenger> passengers) {
		return service.save(passengers);
	}
	
	@GetMapping("/passengers")
	@Operation(summary = "Fetch All Records")
	public ResponseEntity<Object> fetchPassengers() {
		return service.fetchAll();
	}
	
	@GetMapping("/passengers/{id}")
	@Operation(summary = "Fetch By Id")
	public ResponseEntity<Object> fetchById(@PathVariable int id) {
		return service.fetchById(id);
	}
	
	@GetMapping("/passengers/name/{name}")
	@Operation(summary = "Fetch By Name")
	public ResponseEntity<Object> fetchByName(@PathVariable String name) {
		return service.fetchByName(name);
	}
	
	@GetMapping("/passengers/mobile/{mobile}")
	@Operation(summary = "Fetch By Mobile")
	public ResponseEntity<Object> fetchByMobile(@PathVariable String Mobile) {
		return service.fetchByMobile(Mobile);
	}
	
	@GetMapping("/passengers/trackid/{trackid}")
	@Operation(summary = "Fetch By trackid")
	public ResponseEntity<Object> fetchByResult(@PathVariable String trackid) {
		return service.fetchByTrackId(trackid);
	}
	
	@DeleteMapping("/passengers/{id}")
	@Operation(summary = " Delete By Id")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		return service.delete(id);
	}
	
	@PutMapping("/passengers")
	@Operation(summary = "Update - PUT")
	public ResponseEntity<Object> updateStudent(@RequestBody Passenger passenger) {
		return service.update(passenger);
	}
	
	@PatchMapping("/passengers/{id}")
	@Operation(summary = "Update - Patch")
	public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Passenger passenger) {
		return service.update(passenger, id);
	}
	
}
