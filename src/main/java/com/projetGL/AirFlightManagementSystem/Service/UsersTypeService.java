package com.projetGL.AirFlightManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projetGL.AirFlightManagementSystem.Entity.UsersType;
import com.projetGL.AirFlightManagementSystem.Repository.UsersTypeRepository;

@Service
public class UsersTypeService {
	
	private final UsersTypeRepository usersTypeRepsitory;

	public UsersTypeService(UsersTypeRepository usersTypeRepsitory) {
		super();
		this.usersTypeRepsitory = usersTypeRepsitory;
	}
	
	
	public List<UsersType>getAll(){
		return usersTypeRepsitory.findAll();
		
		
	}
	
	

}
