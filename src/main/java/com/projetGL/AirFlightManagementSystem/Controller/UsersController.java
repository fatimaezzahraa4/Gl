package com.projetGL.AirFlightManagementSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.projetGL.AirFlightManagementSystem.Entity.Users;
import com.projetGL.AirFlightManagementSystem.Entity.UsersType;
import com.projetGL.AirFlightManagementSystem.Service.UsersService;
import com.projetGL.AirFlightManagementSystem.Service.UsersTypeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class UsersController {
	
	
	  private final UsersTypeService userTypeService ;
      private final UsersService userService; 
      
      
      @Autowired
	  public UsersController(UsersTypeService userTypeService, UsersService userService) {
		this.userTypeService = userTypeService;
		this.userService = userService;
	   }

	
	@GetMapping("/register")
	public String register(Model model)
	{
	   List<UsersType> usersType=userTypeService.getAll();
	   model.addAttribute("getAllTypes", usersType);
	   model.addAttribute("user",new Users());
       return"register";
   }
	
	
	
	@PostMapping("/register/new")
	 public String UserRegistration (@Valid Users users ,Model model)
	{
		Optional<Users> optionalUser = userService.getUserByEmail(users.getEmail());
		if(optionalUser.isPresent()) 
		  {
			  model.addAttribute("error","Email already regestred , try to login or regester with other email.");
			  List<UsersType> usersType=userTypeService.getAll();
			  model.addAttribute("getAllTypes", usersType);
			  model.addAttribute("user",new Users());
		      return"register";
		  }
	
		 
         userService.addNew(users);
		 return "redirect:/dashboard/";
	 }
	
	
	@GetMapping("/login")
	public String login() {
		return"login";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request ,HttpServletResponse response) {
		Authentication authentication =SecurityContextHolder.getContext()
		.getAuthentication();
		
		if(authentication !=null) {
			new SecurityContextLogoutHandler().logout(request , response, authentication );
	}
   return "redirect:/";

	}

}
