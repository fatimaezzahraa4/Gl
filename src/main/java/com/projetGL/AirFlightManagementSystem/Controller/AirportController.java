package com.projetGL.AirFlightManagementSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  // Importer Model pour la gestion des vues
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.projetGL.AirFlightManagementSystem.Entity.Airport;
import com.projetGL.AirFlightManagementSystem.Repository.AirportRepository;
import com.projetGL.AirFlightManagementSystem.Service.AirportService;

	@RequestMapping("/dashboard")
	@Controller
	public class AirportController {
		
	    @Autowired
	    private AirportRepository repo;
	    @Autowired
	    private AirportService airportService;


	    @GetMapping("/manageAirports")
	    public String manageAirports(Model model) {
	        List<Airport> airports = repo.findAll();
	        model.addAttribute("airports", airports);
	        model.addAttribute("airport", new Airport());  // Adding an empty airport to the model for the form
	        return "manageAirports";
	    }

	    @PostMapping("/addAirport")
	    public String addAirport(@ModelAttribute("airport") Airport airport) {
	        repo.save(airport);
	        return "redirect:/dashboard/manageAirports";
	    }

	    @DeleteMapping("/airport/{id}")
	    public String deleteAirport(@PathVariable Integer id) {
	        Optional<Airport> airport = repo.findById(id);
	        if (airport.isPresent()) {
	            repo.deleteById(id);
	        }
	        return "redirect:/dashboard/manageAirports";
	    }

	    // Additional edit method (if needed)
	    @GetMapping("/editAirport/{id}")
	    public String editAirport(@PathVariable Integer id, Model model) {
	        Optional<Airport> airport = repo.findById(id);
	        if (airport.isPresent()) {
	            model.addAttribute("airport", airport.get());
	            return "editAirport";  // You can create a specific "editAirport.html" template
	        }
	        return "redirect:/dashboard/manageAirports";
	    }

	    @PostMapping("/updateAirport/{id}")  // Ajoute l'ID dans l'URL pour indiquer l'aéroport à mettre à jour
	    @ResponseBody
	    public ResponseEntity<Airport> updateAirport(@PathVariable Integer id, @RequestBody Airport airport) {
	        try {
	            Airport updatedAirport = airportService.updateAirport(id, airport);  // Passe l'ID et l'aéroport mis à jour
	            if (updatedAirport != null) {
	                return ResponseEntity.ok(updatedAirport);  // Retourner la réponse avec l'aéroport mis à jour
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Si l'aéroport n'existe pas
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Erreur interne
	        }
	    }
	
	}

