package com.projetGL.AirFlightManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerFlightSave;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;
import com.projetGL.AirFlightManagementSystem.Repository.PassengerSaveRepository;

@Service
public class PassengerSaveService   {

	private  final PassengerSaveRepository passengerSaveRepository ;

	
	
	public PassengerSaveService(PassengerSaveRepository passengerSaveRepository) {
		super();
		this.passengerSaveRepository = passengerSaveRepository;
	}
	
	
	public List<PassengerFlightSave> getPassengerFlights(PassengerProfile userAccountId){
	    return passengerSaveRepository.findByUserId(userAccountId);
	}
	
	public List<PassengerFlightSave>getFlightsPassenger(Flight flight){
    return passengerSaveRepository.findByFlight(flight);
	}
	
	

    public void addNew(PassengerFlightSave passengerFlightSave) {
    	passengerSaveRepository.save(passengerFlightSave);
    }


}
