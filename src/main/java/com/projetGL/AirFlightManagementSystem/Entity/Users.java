package com.projetGL.AirFlightManagementSystem.Entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;





@Entity
@Table(name="users")
public class Users {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId ;
	
	
	  @Column(unique = true)
	  private String email;
 
	private String name ;
	
	@NotEmpty
	private String password ;
	
	private boolean isActive;
	
	@DateTimeFormat(pattern ="dd-MM-yyyy")
	private Date registrationDate ;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userTypeId", referencedColumnName = "userTypeId")
	private UsersType userTypeId;


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Date getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	public UsersType getUserTypeId() {
		return userTypeId;
	}


	public void setUserTypeId(UsersType userTypeId) {
		this.userTypeId = userTypeId;
	}


	public Users(int userId, String email, String name, String password, boolean isActive, Date registrationDate,
			UsersType userTypeId) {
		super();
		this.userId = userId;
		this.email = email;
		this.name = name;
		this.password = password;
		this.isActive = isActive;
		this.registrationDate = registrationDate;
		this.userTypeId = userTypeId;
	}


	public Users() {
		super();
	}


	@Override
	public String toString() {
		return "Users [userId=" + userId + ", email=" + email + ", name=" + name + ", password=" + password
				+ ", isActive=" + isActive + ", registrationDate=" + registrationDate + ", userTypeId=" + userTypeId
				+ "]";
	}
	
	
	
	
	
	

}