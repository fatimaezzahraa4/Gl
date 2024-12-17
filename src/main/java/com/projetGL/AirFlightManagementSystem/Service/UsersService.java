package com.projetGL.AirFlightManagementSystem.Service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.AdminProfile;
import com.projetGL.AirFlightManagementSystem.Entity.PassengerProfile;
import com.projetGL.AirFlightManagementSystem.Entity.Users;
import com.projetGL.AirFlightManagementSystem.Repository.AdminProfileRepository;
import com.projetGL.AirFlightManagementSystem.Repository.PassengerProfileRepository;
import com.projetGL.AirFlightManagementSystem.Repository.UsersRepository;


@Service
public class UsersService {
 
	  private final UsersRepository usersRepository;
	  private final AdminProfileRepository adminProfileRepository;
	  private final PassengerProfileRepository passengerProfileRepository;
	  private final PasswordEncoder passwordEncoder;

	    @Autowired
	   public UsersService(UsersRepository usersRepository, AdminProfileRepository adminProfileRepository,
				PassengerProfileRepository passengerProfileRepository, PasswordEncoder passwordEncoder) {
			super();
			this.usersRepository = usersRepository;
			this.adminProfileRepository = adminProfileRepository;
			this.passengerProfileRepository = passengerProfileRepository;
			this.passwordEncoder = passwordEncoder;
		}

	   public Users addNew(Users users) {
	        users.setActive(true);
	        users.setRegistrationDate(new Date(System.currentTimeMillis()));
	        users.setPassword(passwordEncoder.encode(users.getPassword()));
	        Users savedUser = usersRepository.save(users);
	        int userTypeId = users.getUserTypeId().getUserTypeId();

	        if (userTypeId == 1) {
	        	adminProfileRepository.save(new AdminProfile(savedUser));	        }
	        else {
	        	passengerProfileRepository.save(new PassengerProfile(savedUser));
	        }

	        return savedUser;
	    }

	    

		public Object getCurrentUserProfile() {

	        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();

	        
	        if (!(authentication instanceof AnonymousAuthenticationToken)) {
	            String username = authentication.getName();
	            Users users = usersRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Could not found " + "user"));
	            int userId = users.getUserId();
	            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Admin"))) {
	            	AdminProfile  adminProfile = adminProfileRepository.findById(userId).orElse(new AdminProfile());
	                return adminProfile;
	            } else {
	            	PassengerProfile passengerProfile =  passengerProfileRepository. findById(userId).orElse(new PassengerProfile());
	                return passengerProfile;
	            }
	        }

	        return null;
	    }

	    public Optional<Users> getUserByEmail(String email) {
	        return usersRepository.findByEmail(email);
	    }

		public Users getCurrentUser() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (!(authentication instanceof AnonymousAuthenticationToken)) {
	            String username = authentication.getName();
	            Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not found " + "user"));
	            return user;
	        }

	        return null;
		}

		public Users findByEmail(String currentUsername) {
	        return usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not " +
	                "found"));
	    }

		
		
		
}
		
			
		
        

		
		

	
	
	
	
	
	
	

