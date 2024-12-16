package com.projetGL.AirFlightManagementSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetGL.AirFlightManagementSystem.Entity.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users,Integer>{

	Optional<Users> findByEmail(String email);
	
}
