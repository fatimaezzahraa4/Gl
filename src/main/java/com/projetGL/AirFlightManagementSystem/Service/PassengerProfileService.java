package com.projetGL.AirFlightManagementSystem.Service;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;
import com.projetGL.AirFlightManagementSystem.Entity.Users;
import com.projetGL.AirFlightManagementSystem.Repository.PassengerProfileRepository;
import com.projetGL.AirFlightManagementSystem.Repository.UsersRepository;

@Service
public class PassengerProfileService {
	
	
	private final PassengerProfileRepository passengerProfileRepository;
    private final UsersRepository usersRepository;
    
    


	public PassengerProfileService(PassengerProfileRepository passengerProfileRepository,
			UsersRepository usersRepository) {
		super();
		this.passengerProfileRepository = passengerProfileRepository;
		this.usersRepository = usersRepository;
	}


	public Optional<PassengerProfile> getOne(Integer id) {
        return passengerProfileRepository.findById(id);
    }


	public PassengerProfile addNew(PassengerProfile passengerProfile) {
		return passengerProfileRepository.saveAndFlush(passengerProfile);
	}


	public PassengerProfile getCurrentPssengerProfile() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (!(authentication instanceof AnonymousAuthenticationToken)) {
	            String currentUsername = authentication.getName();
	            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	            Optional<PassengerProfile> 	PpassengerProfile = getOne(users.getUserId());
	            return PpassengerProfile.orElse(null);
	        } else return null;

	    }


}
