package com.projetGL.AirFlightManagementSystem.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.AdminFlightsDto;
import com.projetGL.AirFlightManagementSystem.Entity.Airport;
import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Entity.IAdminFlight;
import com.projetGL.AirFlightManagementSystem.Repository.FlightPostActivityRepository;


@Service

public class FlightPostActivityService {


	    private final FlightPostActivityRepository flightPostActivityRepository;

	    public FlightPostActivityService(FlightPostActivityRepository flightPostActivityRepository) {
	        this.flightPostActivityRepository = flightPostActivityRepository;
	    }
	    
	 

	   
	    

		public Flight addNew(Flight flightPostActivity) {
	        return flightPostActivityRepository.save(flightPostActivity);
		}


	   	    
	    public List<AdminFlightsDto> getAdminFlights(int adminId) {
	    	
	    	
	        // Récupère les données depuis la base via l'interface IAdminFlight
	        List<IAdminFlight> adminFlights = flightPostActivityRepository.getAdminFlights(adminId);

	        // Liste pour stocker les objets AdminFlightDto
	        List<AdminFlightsDto> adminFlightDtoList = new ArrayList<>();

	        for (IAdminFlight flight : adminFlights) {
	            // Création des objets pour les informations d'origine et de destination
	        	Airport origin = new Airport(flight.getOrigin());
	            Airport destination = new Airport(flight.getdestination());

	            // Création et ajout du DTO dans la liste
	            adminFlightDtoList.add(new AdminFlightsDto(
	                    flight.getTotalReservations(),
	                    flight.getFlight_post_id(),
	                    flight.getFlght_title(),
	                    flight.getAirline(),
	                    origin,
	                    destination,
	                    flight.getflightDate()
	            ));
	        }

	        return adminFlightDtoList;
	    }

	 
	     
	    public Flight getOne(Integer id) {
	        return flightPostActivityRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Flight not found"));
	    }






		public List<Flight> getAllFlights() {
			return flightPostActivityRepository.findAll();
		}






		  public Flight getOne(int id) {

		        return flightPostActivityRepository.findById(id).orElseThrow(()->new RuntimeException("flight not found"));
		    }



		public List<Flight> searchFlights(String flight, String origin, String destination, String airline,
				String status, LocalDate searchDate) {
			return Objects.isNull(searchDate) ? flightPostActivityRepository.searchWithoutDate(flight, origin,destination, airline,status) :
                flightPostActivityRepository.search(flight, origin,destination, airline,status, searchDate);
		}






		
	}



