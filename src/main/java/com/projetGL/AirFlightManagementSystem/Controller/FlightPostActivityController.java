package com.projetGL.AirFlightManagementSystem.Controller;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetGL.AirFlightManagementSystem.Entity.AdminFlightsDto;
import com.projetGL.AirFlightManagementSystem.Entity.AdminProfile;
import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerFlightSave;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerReservation;
import com.projetGL.AirFlightManagementSystem.Service.FlightPostActivityService;
import com.projetGL.AirFlightManagementSystem.Service.PassengerReservationService;
import com.projetGL.AirFlightManagementSystem.Service.PassengerSaveService;
import com.projetGL.AirFlightManagementSystem.Service.UsersService;

import jakarta.validation.Valid;

@Controller
public class FlightPostActivityController {

	
	private final  UsersService userService;
    private final FlightPostActivityService flightPostActivityService;
    
    private final PassengerReservationService passengerReservationService;
    
    
    private final PassengerSaveService passengerSaveFlight;

	
	public FlightPostActivityController(UsersService userService, FlightPostActivityService flightPostActivityService  ,PassengerReservationService passengerReservationService,PassengerSaveService passengerSaveFlight) {
		super();
		this.userService = userService;
		this.flightPostActivityService = flightPostActivityService;
		this.passengerReservationService= passengerReservationService;
		this.passengerSaveFlight = passengerSaveFlight;
	}
	
	@GetMapping("/dashboard/")
	public String searchFlights(Model model,
	                            @RequestParam(value = "flight", required = false) String flight,
	                            @RequestParam(value = "origin", required = false) String origin,
	                            @RequestParam(value = "destination", required = false) String destination,
	                            @RequestParam(value = "airline", required = false) String airline,
	                            @RequestParam(value = "status", required = false) String status,
	                            @RequestParam(value = "today", required = false) boolean today,
	                            @RequestParam(value = "days7", required = false) boolean days7,
	                            @RequestParam(value = "days30", required = false) boolean days30) {

	    // Ajouter les critères de recherche au modèle
	    model.addAttribute("flight", flight);
	    model.addAttribute("origin", origin);
	    model.addAttribute("destination", destination);
	    model.addAttribute("airline", airline);
	    model.addAttribute("status", status);
	    model.addAttribute("today", today);
	    model.addAttribute("days7", days7);
	    model.addAttribute("days30", days30);

	    // Initialisation des variables pour la recherche
	    LocalDate searchDate = null;
	    List<Flight> flightPost = null;
	    boolean dateSearchFlag = true;

	    // Traitement de la recherche par date
	    if (days30) {
	        searchDate = LocalDate.now().minusDays(30);
	    } else if (days7) {
	        searchDate = LocalDate.now().minusDays(7);
	    } else if (today) {
	        searchDate = LocalDate.now();
	    } else {
	        dateSearchFlag = false;
	    }

	    // Recherche des vols en fonction des critères
	    if (!dateSearchFlag && !StringUtils.hasText(flight) && !StringUtils.hasText(origin) && !StringUtils.hasText(destination)
	            && !StringUtils.hasText(airline) && !StringUtils.hasText(status)) {
	        // Si aucun critère n'est spécifié, on récupère tous les vols
	        flightPost = flightPostActivityService.getAllFlights();
	    } else {
	        // Recherche des vols en fonction des critères fournis
	    	flightPost = flightPostActivityService.searchFlights(flight, origin, destination, airline, status, searchDate);
	    }

	    // Ajouter les résultats de la recherche au modèle
	    model.addAttribute("flights", flightPost);

	    // Gestion de l'utilisateur courant
	    Object currentUserProfile = userService.getCurrentUserProfile();
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (!(authentication instanceof AnonymousAuthenticationToken)) {
	        String currentUsername = authentication.getName();
	        model.addAttribute("username", currentUsername);

	       if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Admin"))) {
	            if (currentUserProfile instanceof AdminProfile) {
	                AdminProfile adminProfile = (AdminProfile) currentUserProfile;
	                List<AdminFlightsDto> adminFlights = flightPostActivityService.getAdminFlights(adminProfile.getUserAccountId());
	                model.addAttribute("flightPost", adminFlights);
	            }
	        } else  {
	        	PassengerProfile  passengerProfile = (PassengerProfile)currentUserProfile;
	            List<PassengerReservation> passengerReservationList = passengerReservationService.getPassengerFlights((PassengerProfile)currentUserProfile);
	            List<PassengerFlightSave> passengerSaveList= passengerSaveFlight.getPassengerFlights((PassengerProfile)currentUserProfile);
	            //model.addAttribute("reservations", passengerReservationList);
	            
	            boolean exist;
	            boolean saved;
	            
	            for(Flight flightActivity : flightPost) {
	            	exist=false;
	            	saved=false;
	            	for(PassengerReservation Reservation :passengerReservationList) {
                        if (Objects.equals(flightActivity.getFlightId(),  Reservation.getFlight().getFlightId()) ){

	            			flightActivity.setIsActive(true);
	            			exist=true;
	            			break;
	            		}
	            		  
	            	}
	            	for (PassengerFlightSave passengerSave : passengerSaveList) {
                        if (Objects.equals(flightActivity.getFlightId(), passengerSave.getFlight().getFlightId())) {

                            flightActivity.setIsSaved(true);
                            saved = true;
                            break;
                        }
                    }

                    if (!exist) {
                        flightActivity.setIsActive(false);
                    }
                    if (!saved) {
                        flightActivity.setIsSaved(false);
                    }

                    model.addAttribute("flightPost", flightPost);

                }
            }
	            	
	            }
	       

	    model.addAttribute("user", currentUserProfile);
	    return "dashboard";
	}

	
	@GetMapping("/global-search/")
	public String globalSearch(Model model,
            @RequestParam(value = "flight", required = false) String flight,
            @RequestParam(value = "origin", required = false) String origin,
            @RequestParam(value = "destination", required = false) String destination,
            @RequestParam(value = "airline", required = false) String airline,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "today", required = false) boolean today,
            @RequestParam(value = "days7", required = false) boolean days7,
            @RequestParam(value = "days30", required = false) boolean days30) {
		
		
		 // Ajouter les critères de recherche au modèle
	    model.addAttribute("flight", flight);
	    model.addAttribute("origin", origin);
	    model.addAttribute("destination", destination);
	    model.addAttribute("airline", airline);
	    model.addAttribute("status", status);
	    model.addAttribute("today", today);
	    model.addAttribute("days7", days7);
	    model.addAttribute("days30", days30);

	    // Initialisation des variables pour la recherche
	    LocalDate searchDate = null;
	    List<Flight> flightPost = null;
	    boolean dateSearchFlag = true;

	    // Traitement de la recherche par date
	    if (days30) {
	        searchDate = LocalDate.now().minusDays(30);
	    } else if (days7) {
	        searchDate = LocalDate.now().minusDays(7);
	    } else if (today) {
	        searchDate = LocalDate.now();
	    } else {
	        dateSearchFlag = false;
	    }
	    // Traitement de la recherche par date
	    if (days30) {
	        searchDate = LocalDate.now().minusDays(30);
	    } else if (days7) {
	        searchDate = LocalDate.now().minusDays(7);
	    } else if (today) {
	        searchDate = LocalDate.now();
	    } else {
	        dateSearchFlag = false;
	    }

	    // Recherche des vols en fonction des critères
	    if (!dateSearchFlag && !StringUtils.hasText(flight) && !StringUtils.hasText(origin) && !StringUtils.hasText(destination)
	            && !StringUtils.hasText(airline) && !StringUtils.hasText(status)) {
	        // Si aucun critère n'est spécifié, on récupère tous les vols
	        flightPost = flightPostActivityService.getAllFlights();
	    } else {
	        // Recherche des vols en fonction des critères fournis
	    	flightPost = flightPostActivityService.searchFlights(flight, origin, destination, airline, status, searchDate);
	    }

	    // Ajouter les résultats de la recherche au modèle
	    model.addAttribute("flightPost", flightPost);
	    return "global-search";

	}
	
	
	
	
	
	
	
	@GetMapping("dashboard/add")
	public String addFlights(Model model) {
		model.addAttribute("flight", new Flight());
		model.addAttribute("user",userService.getCurrentUserProfile());
		
		return "add-flight";
		
	}
	
	 @PostMapping("/dashboard/addNew")
	public String  addNew( @Valid Flight flight , BindingResult result, Model model){
		
		 if (result.hasErrors()) {
		        return "add-flight"; // Retourne à la page du formulaire avec les erreurs
		    }
		 
        model.addAttribute("flight", flight);
        Flight saved = flightPostActivityService.addNew(flight);
        return "redirect:/dashboard/";
    
	
		
	}
	
	
	
	
	
}
