package com.projetGL.AirFlightManagementSystem.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@Autowired
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
	
	
	
	 if (!(authentication instanceof AnonymousAuthenticationToken)) {
         Users user = usersRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found."));
         Optional<PassengerProfile> PpassengerProfile = passengerProfileService.getOne(user.getUserId());
         if (PpassengerProfile.isPresent()) {
        	 passengerProfile = PpassengerProfile.get();
            
         }

         model.addAttribute("profile", passengerProfileService);
     }

        	 
	
		return"passenger-profile";
		
	}
	
	
    
	@PostMapping("/addNew")
    public String addNew(PassengerProfile passengerProfile,
                         @RequestParam("image") MultipartFile image,
                         @RequestParam("pdf") MultipartFile pdf,
                         Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Users user = usersRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found."));
            passengerProfile.setUserId(user);
            passengerProfile.setUserAccountId(user.getUserId());
        }

        model.addAttribute("profile", passengerProfile);

      

        String imageName = "";

        if (!Objects.equals(image.getOriginalFilename(), "")) {
            imageName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            passengerProfile.setProfilePhoto(imageName);
        }

        

        try {
            String uploadDir = "photos/candidate/" + passengerProfile.getUserAccountId();
            if (!Objects.equals(image.getOriginalFilename(), "")) {
                FileUploadUtil.saveFile(uploadDir, imageName, image);
            }
            
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return "redirect:/dashboard/";
    }
	

}
