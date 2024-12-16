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

import com.projetGL.AirFlightManagementSystem.Entity.Airplane;
import com.projetGL.AirFlightManagementSystem.Repository.AiplaneRepository;


@RequestMapping("/aiplane")
@Controller
@RestController
public class AiplaneController {
	
	@Autowired
    private AiplaneRepository repo;

    // Récupérer tous les avions
    @GetMapping
    public List<Airplane> getAllAirplanes() {
        return repo.findAll();
    }

    // Récupérer un avion par ID
    @GetMapping("/{id}")
    public ResponseEntity<Airplane> getAirplane(@PathVariable Integer id) {
        Optional<Airplane> airplane = repo.findById(id);
        if (airplane.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'avion n'existe pas
        }
        return ResponseEntity.ok(airplane.get());
    }

    // Ajouter un avion
    @PostMapping
    public ResponseEntity<Airplane> createAirplane(@RequestBody Airplane airplane) {
        Airplane newAirplane = repo.save(airplane);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAirplane);
    }

    // Mettre à jour un avion
    @PutMapping("/{id}")
    public ResponseEntity<Airplane> updateAirplane(@PathVariable Integer id, @RequestBody Airplane updatedAirplane) {
        Optional<Airplane> optionalAirplane = repo.findById(id);
        if (optionalAirplane.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'avion n'existe pas
        }

        // Récupérer l'objet existant
        Airplane existingAirplane = optionalAirplane.get();

        // Mettre à jour les champs
        existingAirplane.setAirline(updatedAirplane.getAirline());
        existingAirplane.setModel(updatedAirplane.getModel());
        existingAirplane.setSeatCapacity(updatedAirplane.getSeatCapacity());
        existingAirplane.setYearOfManufacture(updatedAirplane.getYearOfManufacture());

        // Sauvegarder les modifications
        Airplane savedAirplane = repo.save(existingAirplane);

        return ResponseEntity.ok(savedAirplane);
    }

    // Supprimer un avion
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removeAirplane(@PathVariable Integer id) {
        Optional<Airplane> airplane = repo.findById(id);
        if (airplane.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retourne 404 si l'avion n'existe pas
        }

        repo.delete(airplane.get());
        return ResponseEntity.noContent().build();
    }
}


