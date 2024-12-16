package com.projetGL.AirFlightManagementSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Repository.FlightRepository;



@RequestMapping("/flight")
@Controller
@RestController
public class FlightController {
	
	@Autowired
	FlightRepository repo;
	//get all the students 
	@GetMapping("/flights")
	public List<Flight> getAllFlights(){
		 List<Flight> flights = repo.findAll();
		  return flights;
	}
	
	//localhost:8080/students/1
	@GetMapping("/flights/{id}")
	public Flight getFlight(@PathVariable Integer id) {
		Flight flight = repo.findById(id).get();
		
		return flight;
		
	}
	
	@PostMapping("/flight/add")
	public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
		Flight newFlight = repo.save(flight);
		return ResponseEntity.status(HttpStatus.CREATED).body(newFlight);
		
	}
	
	@PutMapping("/flight/update/{id}")
	public ResponseEntity<Flight> updateFlight(@PathVariable Integer id, @RequestBody Flight updatedFlight) {
	    // Vérifiez si le vol existe
	    Optional<Flight> optionalFlight = repo.findById(id);
	    if (optionalFlight.isEmpty()) {
	        return ResponseEntity.notFound().build(); // Retourne 404 si le vol n'existe pas
	    }

	    // Récupérer l'objet existant
	    Flight existingFlight = optionalFlight.get();

	    // Mettre à jour les champs
	    existingFlight.setOrigin(updatedFlight.getOrigin());
	    existingFlight.setDestination(updatedFlight.getDestination());
	    existingFlight.setAirline(updatedFlight.getAirline());
	    existingFlight.setTicketPrice(updatedFlight.getTicketPrice());
	    existingFlight.setDuration(updatedFlight.getDuration());
	    existingFlight.setDepartureDate(updatedFlight.getDepartureDate());
	    existingFlight.setArrivalDate(updatedFlight.getArrivalDate());

	    // Sauvegarder les modifications
	    repo.save(existingFlight);

	    // Retourner le vol mis à jour
	    return ResponseEntity.ok(existingFlight);
	}

	
	
	@DeleteMapping("/flight/delete/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeFlight(@PathVariable int id) {
		Flight flight = repo.findById(id).get();
		repo.delete(flight);
	}

}
