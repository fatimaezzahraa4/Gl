package com.projetGL.AirFlightManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerReservation;

	public interface PassergerReservationRepository extends JpaRepository<PassengerReservation, Integer> {

	
	
	
	List<PassengerReservation> findByUserId(PassengerProfile userId);
	
	List<PassengerReservation> findByFlight(Flight flight);
}
