package com.projetGL.AirFlightManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.Airport;
import com.projetGL.AirFlightManagementSystem.Repository.AirportRepository;


@Service
public class AirportService {
	
	

    @Autowired
    private AirportRepository repo;

    // Récupérer tous les aéroports
    public List<Airport> getAllAirports() {
        return repo.findAll();
    }

    // Récupérer un aéroport par son ID
    public Optional<Airport> getAirportById(Integer id) {
        return repo.findById(id);
    }

    // Créer un nouvel aéroport
    public Airport createAirport(Airport airport) {
        return repo.save(airport);
    }

    // Mettre à jour un aéroport existant
    public Airport updateAirport(Integer id, Airport updatedAirport) {
        Optional<Airport> existingAirportOpt = repo.findById(id);
        if (existingAirportOpt.isPresent()) {
            Airport existingAirport = existingAirportOpt.get();
            existingAirport.setIATACode(updatedAirport.getIATACode());
            existingAirport.setName(updatedAirport.getName());
            existingAirport.setCountry(updatedAirport.getCountry());
            existingAirport.setCity(updatedAirport.getCity());
            return repo.save(existingAirport); // Enregistre et retourne l'aéroport mis à jour
        }
        return null; // Retourne null si l'aéroport n'existe pas
    }


    // Supprimer un aéroport
    public boolean deleteAirport(Integer id) {
        Optional<Airport> airport = repo.findById(id);
        if (airport.isPresent()) {
            repo.delete(airport.get());
            return true;
        }
        return false; // Retourne false si l'aéroport n'existe pas
    }
}


