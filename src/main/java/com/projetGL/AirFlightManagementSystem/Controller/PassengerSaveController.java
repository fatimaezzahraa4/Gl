package com.projetGL.AirFlightManagementSystem.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerFlightSave;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;
import com.projetGL.AirFlightManagementSystem.Entity.Users;
import com.projetGL.AirFlightManagementSystem.Service.FlightPostActivityService;
import com.projetGL.AirFlightManagementSystem.Service.PassengerProfileService;
import com.projetGL.AirFlightManagementSystem.Service.PassengerSaveService;
import com.projetGL.AirFlightManagementSystem.Service.UsersService;

@Controller
public class PassengerSaveController {

	
	private final UsersService usersService;
    private final PassengerProfileService  passengerProfileService;
    private final FlightPostActivityService flightPostActivityService;
    private final PassengerSaveService  passengerSaveService;
	
	
	
	public PassengerSaveController(UsersService usersService, PassengerProfileService passengerProfileService,
			FlightPostActivityService flightPostActivityService, PassengerSaveService passengerSaveService) {
		super();
		this.usersService = usersService;
		this.passengerProfileService = passengerProfileService;
		this.flightPostActivityService = flightPostActivityService;
		this.passengerSaveService = passengerSaveService;
	}

	public String save(@PathVariable("id") int id ,PassengerFlightSave passengerFlightSave) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users user = usersService.findByEmail(currentUsername);
            Optional<PassengerProfile> ppassengerProfile = passengerProfileService.getOne(user.getUserId());
            Flight flightPostActivity = flightPostActivityService.getOne(id);
            if (ppassengerProfile.isPresent() && flightPostActivity != null) {
            	passengerFlightSave.setFlight(flightPostActivity);
            	passengerFlightSave.setUserId(ppassengerProfile.get());
            } else {
                throw new RuntimeException("User not found");
            }
            passengerSaveService.addNew(passengerFlightSave);
        }
        return "redirect:/dashboard/";
    }

    @GetMapping("saved-flights/")
    public String savedFlights(Model model) {

        List<Flight> flightPost = new ArrayList<>();
        Object currentUserProfile = usersService.getCurrentUserProfile();

        List<PassengerFlightSave> jobSeekerSaveList = passengerSaveService.getPassengerFlights((PassengerProfile) currentUserProfile);
        for (PassengerFlightSave jobSeekerSave : jobSeekerSaveList) {
            flightPost.add(jobSeekerSave.getFlight());
        }

        model.addAttribute("jobPost", flightPost);
        model.addAttribute("user", currentUserProfile);

        return "saved-flights";
    }
	}
	
	
	

