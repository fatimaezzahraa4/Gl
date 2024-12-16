package com.projetGL.AirFlightManagementSystem.Service;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.AdminProfile;
import com.projetGL.AirFlightManagementSystem.Entity.Users;
import com.projetGL.AirFlightManagementSystem.Repository.AdminProfileRepository;
import com.projetGL.AirFlightManagementSystem.Repository.UsersRepository;

@Service
public class AdminProfileService {
	private final AdminProfileRepository adminRepository;
    private final UsersRepository usersRepository;


	
	
	
	public AdminProfileService(AdminProfileRepository adminRepository,UsersRepository usersRepository) {
		super();
		this.adminRepository = adminRepository;
		this.usersRepository =usersRepository;
	}

	
	public Optional<AdminProfile>getOne(Integer id){
		return adminRepository.findById(id);
	}



	public AdminProfile addNew(AdminProfile adminProfile) {
		
		return adminRepository.save(adminProfile );
	}


	public AdminProfile getCurrentAdminProfile() {
	
	     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	       if (!(authentication instanceof AnonymousAuthenticationToken)) {
	          String currentUsername = authentication.getName();
	          Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	          Optional<AdminProfile> adminProfile = getOne(users.getUserId());
	          return adminProfile.orElse(null);
	          
	          
	        } else return null;
	    }
	

	


}
