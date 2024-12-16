package com.projetGL.AirFlightManagementSystem.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projetGL.AirFlightManagementSystem.Entity.AdminProfile;
import com.projetGL.AirFlightManagementSystem.Entity.Flight;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerFlightSave;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerReservation;
import com.projetGL.AirFlightManagementSystem.Entity.Users;
import com.projetGL.AirFlightManagementSystem.Service.AdminProfileService;
import com.projetGL.AirFlightManagementSystem.Service.FlightPostActivityService;
import com.projetGL.AirFlightManagementSystem.Service.PassengerProfileService;
import com.projetGL.AirFlightManagementSystem.Service.PassengerReservationService;
import com.projetGL.AirFlightManagementSystem.Service.PassengerSaveService;
import com.projetGL.AirFlightManagementSystem.Service.UsersService;

import java.util.List;
import java.util.Optional;


@Controller
public class PassengerReservationController {
	
	
	
	    private final FlightPostActivityService flightPostActivityService;
	    private final UsersService usersService;
	    private final PassengerReservationService passengerReservationService;
	    private final PassengerSaveService passengerSaveService;
	    private final AdminProfileService adminProfileService;
	    private final PassengerProfileService passengerProfileService;


	    @Autowired
	    public PassengerReservationController(FlightPostActivityService flightPostActivityService,
				UsersService usersService, PassengerReservationService passengerReservationService,
				PassengerSaveService passengerSaveService, AdminProfileService adminProfileService,
				PassengerProfileService passengerProfileService) {
			super();
			this.flightPostActivityService = flightPostActivityService;
			this.usersService = usersService;
			this.passengerReservationService = passengerReservationService;
			this.passengerSaveService = passengerSaveService;
			this.adminProfileService = adminProfileService;
			this.passengerProfileService = passengerProfileService;
		}

	   

	    @GetMapping("flight-details-apply/{id}")
	    public String display(@PathVariable("id") Integer id, Model model) {
	    	Flight flightDetails = flightPostActivityService.getOne(id);
	    	
	        List<PassengerReservation> passengerReservationList = passengerReservationService.getFlightPassengers(flightDetails);
	        List<PassengerFlightSave> passengerFlightSaveList = passengerSaveService.getFlightsPassenger(flightDetails);
	        
	        
	        
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (!(authentication instanceof AnonymousAuthenticationToken)) {
	            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Admin"))) {
	                AdminProfile user = adminProfileService.getCurrentAdminProfile();
	                if (user != null) {
	                    model.addAttribute("applyList", passengerReservationList);
	                }
	            } else {
	                PassengerProfile user = passengerProfileService.getCurrentPssengerProfile();
	                if (user != null) {
	                    boolean exists = false;
	                    boolean saved = false;
	                    for (PassengerReservation passengerReserve : passengerReservationList) {
	                        if (passengerReserve.getUserId().getUserAccountId() == user.getUserAccountId()) {
	                            exists = true;
	                            break;
	                        }
	                    }
	                    for (PassengerFlightSave passengerFlightSave : passengerFlightSaveList) {
	                        if (passengerFlightSave.getUserId().getUserAccountId() == user.getUserAccountId()) {
	                            saved = true;
	                            break;
	                        }
	                    }
	                    model.addAttribute("alreadyApplied", exists);
	                    model.addAttribute("alreadySaved", saved);
	                }
	            }
	        }

	        PassengerReservation jobSeekerApply = new PassengerReservation();
	        model.addAttribute("applyJob", jobSeekerApply);

	        model.addAttribute("flightDetails", flightDetails);
	        model.addAttribute("user", usersService.getCurrentUserProfile());
	        return "flight-details";
	    }

	   
		

		@PostMapping("flight-details/reserve/{id}")
	    public String reserve(@PathVariable("id") int id, PassengerReservation passengerReservation) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (!(authentication instanceof AnonymousAuthenticationToken)) {
	            String currentUsername = authentication.getName();
	            Users user = usersService.findByEmail(currentUsername);
	            Optional<PassengerProfile> ppassengerProfile = passengerProfileService.getOne(user.getUserId());
	            Flight flightPostActivity = flightPostActivityService.getOne(id);
	            
	            
	          
	            	
	            	if (ppassengerProfile.isPresent() && flightPostActivity != null) {
	            		passengerReservation = new PassengerReservation();
	            		passengerReservation.setUserId(ppassengerProfile.get());
	            		passengerReservation.setFlight(flightPostActivity);
	            		//passengerReservation.setReservarionDate(new Date());
	            	}
	            	
	            } else {
	                throw new RuntimeException("User not found");
	            
	            //passengerReservationService.addNew(passengerReservation);
	        }

	        return "redirect:/dashboard/";
	    }
	}


