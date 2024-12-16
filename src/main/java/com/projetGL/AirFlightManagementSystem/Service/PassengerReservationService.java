package com.projetGL.AirFlightManagementSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerReservation;
import com.projetGL.AirFlightManagementSystem.Repository.PassergerReservationRepository;

@Service
public class PassengerReservationService {

	private final PassergerReservationRepository passengerReservationRepository;

	
	
	@Autowired
	public PassengerReservationService(PassergerReservationRepository passengerReservationRepository) {
		super();
		this.passengerReservationRepository = passengerReservationRepository;
	}
	
	
	public List<PassengerReservation> getPassengerFlights(PassengerProfile userAccountId){
	
	return passengerReservationRepository.findByUserId(userAccountId);
}
	

	
	public List<PassengerReservation>getFlightPassengers(Flight flight){
		
		return passengerReservationRepository.findByFlight(flight);
	}


	public void addNew(PassengerReservation passengerReservation) {
		
		passengerReservationRepository.save(passengerReservation);
	}


	

	


	
	}
