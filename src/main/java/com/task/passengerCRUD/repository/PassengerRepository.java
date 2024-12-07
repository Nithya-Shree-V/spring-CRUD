package com.task.passengerCRUD.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.task.passengerCRUD.dto.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
	boolean existsByMobile(String string);
	
	List<Passenger> findByName(String name);

	Optional<Passenger> findByMobile(String mobile);

	Optional<Passenger> findByTicket(String trackid);
}
