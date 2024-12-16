package com.projetGL.AirFlightManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetGL.AirFlightManagementSystem.Entity.Flight;

public interface FlightRepository  extends JpaRepository<Flight, Integer>{

}
