package com.projetGL.AirFlightManagementSystem.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projetGL.AirFlightManagementSystem.Repository.UsersRepository;
import com.projetGL.AirFlightManagementSystem.Service.PassengerProfileService;
import com.projetGL.AirFlightManagementSystem.Util.FileUploadUtil;

import jakarta.validation.Valid;

import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerReservation;
import com.projetGL.AirFlightManagementSystem.Entity.Users;

@RequestMapping("/passenger-profile")
@Controller
public class PassengerProfileController {
	
	private PassengerProfileService passengerProfileService;
	private UsersRepository usersRepository;
	
	
	
	public PassengerProfileController(PassengerProfileService passengerProfileService,
			UsersRepository usersRepository) {
		super();
		this.passengerProfileService = passengerProfileService;
		this.usersRepository = usersRepository;
	}
	
	
	
	@GetMapping("/")
	public String PassengerProfile(Model model) {
		
		
	PassengerProfile passengerProfile =new PassengerProfile();
	Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
	
	
	List<PassengerReservation> reservations = new ArrayList<>();
	
	 if (!(authentication instanceof AnonymousAuthenticationToken)) {
         Users user = usersRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found."));
         Optional<PassengerProfile> savedPassengerProfile = passengerProfileService.getOne(user.getUserId());
         if (savedPassengerProfile.isPresent()) {
        	 passengerProfile = savedPassengerProfile.get();
        	 
        	 
            /* if (passengerProfile.getReservations().isEmpty()) {
            	 reservations.add(new PassengerReservation());
                 passengerProfile.setReservations(reservations);
             }
             
             if (passengerProfile.getReservations() == null || passengerProfile.getReservations().isEmpty()) {
                  reservations.add(new PassengerReservation());
                  passengerProfile.setReservations(reservations);
                       } else {
                  reservations = passengerProfile.getReservations();
                     }*/
         }

         model.addAttribute("Reservations", reservations);
         model.addAttribute("profile", passengerProfile);
     }
	
	
		return"passenger-profile";
		
	}
	
	
    
    
	
	/*@PostMapping("/addNew")
	public String addNew(PassengerProfile passengerProfile,
	                     @RequestParam("image") MultipartFile image,
	                     Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (!(authentication instanceof AnonymousAuthenticationToken)) {
	        Users user = usersRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found."));
	        passengerProfile.setUserId(user);
	        passengerProfile.setUserAccountId(user.getUserId());
	    }

	    List<PassengerReservation> reservationsList = new ArrayList<>();
	    model.addAttribute("profile", passengerProfile);
	    model.addAttribute("reservations", reservationsList);

	   for (PassengerReservation reservations : passengerProfile.getReservations()) {
	        reservations.setPassengerProfile(passengerProfile);
	    }

	    String imageName = "";

	    if (!Objects.equals(image.getOriginalFilename(), "")) {
	        imageName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
	        passengerProfile.setProfilePhoto(imageName);
	    }

	    PassengerProfile savedPassengerProfile = passengerProfileService.addNew(passengerProfile);

	    try {
	        String uploadDir = "photos/candidate/" + passengerProfile.getUserAccountId();
	        if (!Objects.equals(image.getOriginalFilename(), "")) {
	            FileUploadUtil.saveFile(uploadDir, imageName, image);
	        }
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }

	    return "redirect:/dashboard/";
	}*/
	@PostMapping("/addNew")
	public String addNew(@Valid @ModelAttribute PassengerProfile passengerProfile,
	                     @RequestParam("image") MultipartFile image,
	                     Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (!(authentication instanceof AnonymousAuthenticationToken)) {
	        Users user = usersRepository.findByEmail(authentication.getName())
	                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
	        
	        // Nouvelle partie : Vérification et mise à jour du profil existant
	        Optional<PassengerProfile> existingProfile = passengerProfileService.getOne(user.getUserId());
	        
	        if (existingProfile.isPresent()) {
	            // Mise à jour du profil existant
	            PassengerProfile profileToUpdate = existingProfile.get();
	            
	            // Copier les nouveaux champs dans le profil existant
	            profileToUpdate.setFirstName(passengerProfile.getFirstName());
	            profileToUpdate.setLastName(passengerProfile.getLastName());
	            profileToUpdate.setCodeIdentiteNationale(passengerProfile.getCodeIdentiteNationale());
	            profileToUpdate.setCountryCode(passengerProfile.getCountryCode());
	            profileToUpdate.setDateOfBirth(passengerProfile.getDateOfBirth());
	            profileToUpdate.setEmail(passengerProfile.getEmail());
	            profileToUpdate.setNationality(passengerProfile.getNationality());
	            profileToUpdate.setPassportNumber(passengerProfile.getPassportNumber());
	            profileToUpdate.setPhoneNumber(passengerProfile.getPhoneNumber());
	            
	            passengerProfile = profileToUpdate;
	        }
	        
	        passengerProfile.setUserId(user);
	        passengerProfile.setUserAccountId(user.getUserId());
	    }

	    List<PassengerReservation> reservationsList = new ArrayList<>();
	    model.addAttribute("profile", passengerProfile);
	    model.addAttribute("reservations", reservationsList);

	    String imageName = "";

	    if (!Objects.equals(image.getOriginalFilename(), "")) {
	        imageName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
	        passengerProfile.setProfilePhoto(imageName);
	    }

	    PassengerProfile savedPassengerProfile = passengerProfileService.addNew(passengerProfile);

	    try {
	        String uploadDir = "photos/candidate/" + passengerProfile.getUserAccountId();
	        if (!Objects.equals(image.getOriginalFilename(), "")) {
	            FileUploadUtil.saveFile(uploadDir, imageName, image);
	        }
	    } catch (IOException ex) {
	        throw new RuntimeException(ex);
	    }

	    return "redirect:/dashboard/";
	}
	
}
