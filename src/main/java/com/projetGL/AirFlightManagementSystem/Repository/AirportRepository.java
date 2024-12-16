package com.projetGL.AirFlightManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetGL.AirFlightManagementSystem.Entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

}
