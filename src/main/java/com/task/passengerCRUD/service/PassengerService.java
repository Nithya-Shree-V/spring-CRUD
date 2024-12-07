package com.task.passengerCRUD.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.task.passengerCRUD.dto.Passenger;
import com.task.passengerCRUD.repository.PassengerRepository;

@Service
public class PassengerService {

	@Autowired
	PassengerRepository repository;

	public ResponseEntity<Object> save(Passenger passenger) {
		if (repository.existsByMobile(passenger.getMobile())) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "Mobile Number Already Exists");
			return new ResponseEntity<Object>(map, HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			passenger.setTicket(passenger.getName().substring(0, 3) + new Random().nextInt(100, 999)
					+ passenger.getMobile().substring(passenger.getMobile().length() - 4));
			repository.save(passenger);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Saved Success");
			map.put("data", passenger);
			return new ResponseEntity<Object>(map, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<Object> save(List<Passenger> passengers) {
		for (Passenger passenger : passengers) {
			if (repository.existsByMobile(passenger.getMobile())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "Mobile Number Already Exists");
				return new ResponseEntity<Object>(map, HttpStatus.UNPROCESSABLE_ENTITY);
			} else {
				passenger.setTicket(passenger.getName().substring(0, 3) + new Random().nextInt(100, 999)
						+ passenger.getMobile().substring(passenger.getMobile().length() - 4));
			}
		}
		repository.saveAll(passengers);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Record Saved Success");
		map.put("data", passengers);
		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	public ResponseEntity<Object> fetchAll() {
		List<Passenger> passengers = repository.findAll();
		if (passengers.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Data Present in Database");
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Found Success");
			map.put("data", passengers);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchById(int id) {
		Optional<Passenger> optional = repository.findById(id);
		if (optional.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Record Found with Id: " + id);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Found Success");
			map.put("data", optional.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByName(String name) {
		List<Passenger> passengers = repository.findByName(name);
		if (passengers.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Data Present with Name: " + name);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Found Success");
			map.put("data", passengers);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByMobile(String mobile) {
		Optional<Passenger> passengers = repository.findByMobile(mobile);
		if (passengers.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Data Present with Mobile: " + mobile);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Found Success");
			map.put("data", passengers);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> fetchByTrackId(String trackid) {
		Optional<Passenger> passengers = repository.findByTicket(trackid);
		if (passengers.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Data Present with TrackId: " + trackid);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Found Success");
			map.put("data", passengers);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> delete(int id) {
		if (repository.findById(id).isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Data Present with Id: " + id);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			repository.deleteById(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Deleted Success");
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> update(Passenger passenger) {
		passenger.setTicket(passenger.getName().substring(0, 3) + new Random().nextInt(100, 999)
				+ passenger.getMobile().substring(passenger.getMobile().length() - 4));
		repository.save(passenger);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "Record Updated Success");
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	public ResponseEntity<Object> update(Passenger passenger, int id) {
		if (repository.findById(id).isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "No Data Present with Id: " + id);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		} else {
			Passenger passenger1 = repository.findById(id).get();
			if (passenger.getName() != null)
				passenger1.setName(passenger.getName());

			if (passenger.getGender() != null)
				passenger1.setGender(passenger.getGender());

			if (passenger.getMobile() != null)
				passenger1.setMobile(passenger.getMobile());

			if (passenger.getFromdest() != null)
				passenger1.setFromdest(passenger.getFromdest());

			if (passenger.getTodest() != null)
				passenger1.setTodest(passenger.getTodest());

			if (passenger1.getName() != null && passenger1.getMobile() != null
					&& passenger1.getMobile().length() >= 4) {
				String ticket = passenger1.getName().substring(0, 3) + new Random().nextInt(100, 999)
						+ passenger1.getMobile().substring(passenger1.getMobile().length() - 4);
				passenger1.setTicket(ticket);
			}
			repository.save(passenger1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Updated Success");
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}

}
