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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetGL.AirFlightManagementSystem.Entity.Airport;
import com.projetGL.AirFlightManagementSystem.Repository.AirportRepository;

@RequestMapping("/dashboard")
@Controller
public class AirportController {
    @Autowired
    private AirportRepository repo;

    // Méthode pour afficher la liste des aéroports sur une page HTML
    @GetMapping("/manageAirports")
    public String manageAirports(Model model) {
        List<Airport> airports = repo.findAll();  // Récupérer la liste des aéroports
        model.addAttribute("airports", airports);  // Ajouter la liste au modèle pour l'affichage
        return "manageAirports";  // Nom de la vue (fichier manageAirports.html)
    }

    // Requêtes REST pour gérer les aéroports (ajout, modification, suppression)
    @GetMapping
    public List<Airport> getAllAirports() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirport(@PathVariable Integer id) {
        Optional<Airport> airport = repo.findById(id);
        if (airport.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'aéroport n'existe pas
        }
        return ResponseEntity.ok(airport.get());
    }

    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport newAirport = repo.save(airport);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAirport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Integer id, @RequestBody Airport updatedAirport) {
        Optional<Airport> optionalAirport = repo.findById(id);
        if (optionalAirport.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'aéroport n'existe pas
        }

        // Récupérer l'objet existant
        Airport existingAirport = optionalAirport.get();

        // Mettre à jour les champs
        existingAirport.setIataCode(updatedAirport.getIataCode());
        existingAirport.setName(updatedAirport.getName());
        existingAirport.setCountry(updatedAirport.getCountry());
        existingAirport.setCity(updatedAirport.getCity());
        existingAirport.setAirportCapacity(updatedAirport.getAirportCapacity());


        // Sauvegarder les modifications
        Airport savedAirport = repo.save(existingAirport);

        return ResponseEntity.ok(savedAirport);
        }
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Integer id) {
        Optional<Airport> airport = repo.findById(id);
        if (airport.isEmpty()) {
            return ResponseEntity.notFound().build();  // Retourne 404 si l'aéroport n'existe pas
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();  // Retourne 204 après suppression
    }
    }
