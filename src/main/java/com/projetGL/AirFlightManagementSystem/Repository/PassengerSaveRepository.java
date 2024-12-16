package com.projetGL.AirFlightManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerFlightSave;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;

@Repository
public interface PassengerSaveRepository extends JpaRepository<PassengerFlightSave, Integer> {

	
	
	public List<PassengerFlightSave>findByUserId(PassengerProfile userAccountId);
	
	
	List<PassengerFlightSave>findByFlight(Flight flight);
}
