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

import com.projetGL.AirFlightManagementSystem.Entity.Airplane;
import com.projetGL.AirFlightManagementSystem.Repository.AiplaneRepository;
import com.projetGL.AirFlightManagementSystem.Service.AirplaneService;

@RequestMapping("/dashboard")
@Controller
public class AiplaneController {
    
    @Autowired
    private AiplaneRepository repo;
    @Autowired
    private AirplaneService airplaneService;

    // Afficher la page de gestion des avions
    @GetMapping("/manageAirplane")
    public String manageAirplane(Model model) {
        List<Airplane> airplane = repo.findAll();
        model.addAttribute("airplanes", airplane);
        model.addAttribute("airplane", new Airplane());  // Ajouter un avion vide au modèle pour le formulaire
        return "manageAirplane";  // Assurez-vous que vous avez une vue "manageAirplanes.html"
    }

    // Ajouter un avion
    @PostMapping("/addAirplane")
    public String addAirplane(@ModelAttribute("airplane") Airplane airplane) {
        repo.save(airplane);
        return "redirect:/dashboard/manageAirplane";  // Rediriger après l'ajout
    }

    // Supprimer un avion
    @DeleteMapping("/airplane/{id}")
    public String deleteAirplane(@PathVariable Integer id) {
        Optional<Airplane> airplane = repo.findById(id);
        if (airplane.isPresent()) {
            repo.deleteById(id);
        }
        return "redirect:/dashboard/manageAirplane";  // Rediriger après la suppression
    }

    // Modifier un avion (facultatif, si nécessaire)
    @GetMapping("/editAirplane/{id}")
    public String editAirplane(@PathVariable Integer id, Model model) {
        Optional<Airplane> airplane = repo.findById(id);
        if (airplane.isPresent()) {
            model.addAttribute("airplane", airplane.get());
            return "editAirplane";  // Vous pouvez créer un template "editAirplane.html"
        }
        return "redirect:/dashboard/manageAirplane";  // Rediriger si l'avion n'existe pas
    }

    // Mettre à jour un avion
    @PostMapping("/updateAirplane/{id}")
    @ResponseBody
    public ResponseEntity<Airplane> updateAirplane(@PathVariable Integer id, @RequestBody Airplane airplane) {
        try {
            Airplane updatedAirplane = airplaneService.updateAirplane(id, airplane);  // Appeler la méthode de service pour mettre à jour
            if (updatedAirplane != null) {
                return ResponseEntity.ok(updatedAirplane);  // Retourner l'avion mis à jour
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Si l'avion n'existe pas
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Erreur interne
        }
    }
}
