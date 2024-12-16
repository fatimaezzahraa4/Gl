package com.projetGL.AirFlightManagementSystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;

@Entity
@Table(name="admin_profile")
public class AdminProfile {
	
	
	@Id
	private int userAccountId;
	
	
	@OneToOne
	@JoinColumn(name="user_account_id")
	@MapsId
	private Users userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email ;
	
	private int phoneNumber;
	
	
	@Column(nullable=true, length=64)
	public String profilePhoto ;


	public AdminProfile() {
		super();
	}



    @Version
    private Integer version;
    
	

	public AdminProfile(int userAccountId, Users userId, String firstName, String lastName, String email,
			int phoneNumber, String profilePhoto, Integer version) {
		super();
		this.userAccountId = userAccountId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.profilePhoto = profilePhoto;
		this.version = version;
	}


	public AdminProfile(Users users) {
		
		this.userId=users;
	}


	public int getUserAccountId() {
		return userAccountId;
	}


	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}


	public Users getUserId() {
		return userId;
	}


	public void setUserId(Users userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getProfilePhoto() {
		return profilePhoto;
	}


	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	
	@Transient
	public String getPhotosImagePath() {
		if(profilePhoto ==null)
			return null;
		return"/photos/admin/"+userAccountId+"/"+profilePhoto;
	}
	
	

	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	@Override
	public String toString() {
		return "AdminProfile [userAccountId=" + userAccountId + ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", profilePhoto="
				+ profilePhoto + "]";
	}
	
	

}
