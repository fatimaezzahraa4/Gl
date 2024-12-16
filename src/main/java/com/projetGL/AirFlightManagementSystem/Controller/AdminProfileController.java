package com.projetGL.AirFlightManagementSystem.Controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projetGL.AirFlightManagementSystem.Entity.AdminProfile;
import com.projetGL.AirFlightManagementSystem.Entity.Users;
import com.projetGL.AirFlightManagementSystem.Repository.UsersRepository;
import com.projetGL.AirFlightManagementSystem.Service.AdminProfileService;
import com.projetGL.AirFlightManagementSystem.Util.FileUploadUtil;

@Controller
@RequestMapping("admin-profile")
public class AdminProfileController {
	
	
	private final UsersRepository usersRepository;
	private final AdminProfileService adminProfileService;
	
	
	public AdminProfileController(UsersRepository usersRepository, AdminProfileService adminProfileService) {
	    super();
	    this.usersRepository = usersRepository;
	    this.adminProfileService = adminProfileService;
	}




	@GetMapping("/")
	public String adminProfile(Model model) {
		
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		 if(!(authentication instanceof AnonymousAuthenticationToken)) {
			   String currentUsername= authentication.getName();
			   Users users = usersRepository.findByEmail(currentUsername).orElseThrow(()->new UsernameNotFoundException("Could not"+"Found user"));
			 
			    Optional<AdminProfile>adminProfile =adminProfileService.getOne(users.getUserId());
			 
			    if(!adminProfile.isEmpty())
				    model.addAttribute("profile",adminProfile.get());
			 
		 }
		 return"admin_profile";
	}
	
	@PostMapping("/addNew")
	public String addNew(AdminProfile adminProfile ,@RequestParam("image") MultipartFile multipartFile ,Model model) {
		  
		
		
		 Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		 if(!(authentication instanceof AnonymousAuthenticationToken)) {
			 
			 String curentUserName = authentication.getName();
			 Users users = usersRepository.findByEmail(curentUserName).orElseThrow(()-> new UsernameNotFoundException("Could "+"not found user "));
			 adminProfile.setUserId(users);
			 adminProfile.setUserAccountId(users.getUserId());
			 
		 }
		 
		 model.addAttribute("profile",adminProfile);
		 String fileName=" ";
		 if(!multipartFile.getOriginalFilename().equals("")) {
	            fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
	            adminProfile.setProfilePhoto(fileName);

		 }
		 
	        AdminProfile savedUser = adminProfileService.addNew(adminProfile);
	        
	        String uploadDir ="photos/admin/"+savedUser.getUserAccountId();
	        
	        
	        try {
	            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        	
	       

		 return "redirect:/dashboard/";
	}

}
