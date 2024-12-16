package com.projetGL.AirFlightManagementSystem.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.Users;
import com.projetGL.AirFlightManagementSystem.Repository.UsersRepository;
import com.projetGL.AirFlightManagementSystem.Util.CustomUserDetails;

@Service
public class CustomUserDetailsService  implements UserDetailsService{

	private final UsersRepository usersRepository;

	public CustomUserDetailsService(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("could not find user"));
		
		return new CustomUserDetails(user);
	}
	

}
