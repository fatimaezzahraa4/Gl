package com.projetGL.AirFlightManagementSystem.Entity;



import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="passenger_profile")
public class PassengerProfile {
	
	
	
	 @Id
	    private Integer userAccountId;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_account_id")
	    @MapsId
	    private Users userId;
	    
	    
	    
	    
	    private String firstName ;
		private String lastName;
		private String email;
		
		private String countryCode;
		private String phoneNumber;

		@DateTimeFormat(pattern = "dd-MM-yyyy")
	    private LocalDate dateOfBirth;

	   
	    private String passportNumber; 

	    
	    private String nationality;

	   
	    private String   CodeIdentiteNationale;
	    
	    @Version
	    private Integer version;
	   

	    @Column(nullable = true, length = 64)
	    private String profilePhoto;

	   

	  

	    public PassengerProfile(Users userId) {
	        this.userId = userId;
	    }

	    public Integer getUserAccountId() {
			return userAccountId;
		}



		public void setUserAccountId(Integer userAccountId) {
			this.userAccountId = userAccountId;
		}


		public Users getUserId() {
			return userId;
		}


		public void setUserId(Users userId) {
			this.userId = userId;
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


		public String getCountryCode() {
			return countryCode;
		}

		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}


		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}


		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}



		public String getPassportNumber() {
			return passportNumber;
		}


		public void setPassportNumber(String passportNumber) {
			this.passportNumber = passportNumber;
		}

		public String getNationality() {
			return nationality;
		}


		public void setNationality(String nationality) {
			this.nationality = nationality;
		}

		public String getCodeIdentiteNationale() {
			return CodeIdentiteNationale;
		}




		public void setCodeIdentiteNationale(String codeIdentiteNationale) {
			CodeIdentiteNationale = codeIdentiteNationale;
		}

		public Integer getVersion() {
			return version;
		}



		public void setVersion(Integer version) {
			this.version = version;
		}


		public String getProfilePhoto() {
			return profilePhoto;
		}

		public void setProfilePhoto(String profilePhoto) {
			this.profilePhoto = profilePhoto;
		}


		public PassengerProfile(Integer userAccountId, Users userId, String firstName, String lastName, String email,
				String countryCode, String phoneNumber, LocalDate dateOfBirth, String passportNumber,
				String nationality, String codeIdentiteNationale, Integer version, String profilePhoto) {
			super();
			this.userAccountId = userAccountId;
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.countryCode = countryCode;
			this.phoneNumber = phoneNumber;
			this.dateOfBirth = dateOfBirth;
			this.passportNumber = passportNumber;
			this.nationality = nationality;
			CodeIdentiteNationale = codeIdentiteNationale;
			this.version = version;
			this.profilePhoto = profilePhoto;
		}



		public PassengerProfile() {
			super();
		}



		@Transient
	    public String getPhotosImagePath() {
	        if (profilePhoto == null || userAccountId == null) return null;
	        return "photos/candidate/" + userAccountId + "/" + profilePhoto;
	    }

	    
}