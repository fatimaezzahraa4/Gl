package com.projetGL.AirFlightManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.Airplane;
import com.projetGL.AirFlightManagementSystem.Repository.AiplaneRepository;

@Service
public class AirplaneService {

    @Autowired
    private AiplaneRepository repo;

    // Récupérer tous les avions
    public List<Airplane> getAllAirplanes() {
        return repo.findAll();
    }

    // Récupérer un avion par son ID
    public Optional<Airplane> getAirplaneById(Integer id) {
        return repo.findById(id);
    }

    // Créer un nouvel avion
    public Airplane createAirplane(Airplane airplane) {
        return repo.save(airplane);
    }

    // Mettre à jour un avion existant
    public Airplane updateAirplane(Integer id, Airplane updatedAirplane) {
        Optional<Airplane> existingAirplaneOpt = repo.findById(id);
        if (existingAirplaneOpt.isPresent()) {
            Airplane existingAirplane = existingAirplaneOpt.get();
            existingAirplane.setAirline(updatedAirplane.getAirline());
            existingAirplane.setType(updatedAirplane.getType());
            existingAirplane.setSeatCapacity(updatedAirplane.getSeatCapacity());
            existingAirplane.setYearOfManufacture(updatedAirplane.getYearOfManufacture());
            existingAirplane.setModel(updatedAirplane.getModel());
            return repo.save(existingAirplane);
        }
        return null; // Retourne null si l'avion n'existe pas
    }

    // Supprimer un avion
    public boolean deleteAirplane(Integer id) {
        Optional<Airplane> airplane = repo.findById(id);
        if (airplane.isPresent()) {
            repo.delete(airplane.get());
            return true;
        }
        return false; // Retourne false si l'avion n'existe pas
    }
}
