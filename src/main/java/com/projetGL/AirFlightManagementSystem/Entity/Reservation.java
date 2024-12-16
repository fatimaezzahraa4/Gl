package com.projetGL.AirFlightManagementSystem.Entity;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


/*@Entity
@Table(name = "Reservationsss")

public class Reservation {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "reservation_id")
	    private Long ReservationId;
	    
	 @ManyToOne
	    @JoinColumn(name = "user_id")
	    private Users user;
	 
	 
	 @ManyToOne
	 @JoinColumn(name = "flight_id", nullable = false)
	 private Flight flight; // Relation vers un seul vol et non une liste
	    

		@DateTimeFormat(pattern= "dd-MM-yyyy")
	    private Date reservationDate;

		
		
	    private String status;

	    private double totalPrice;
	    
	    
	    @ManyToOne
	    @JoinColumn(name = "passenger_profile_id", nullable = false)
	    private PassengerProfile passengerProfile;

	    

	    
	    
	    
	    
	    
	    
		public Reservation() {
			super();
		}






		public Reservation(Long reservationId, Users user, Flight flight, Date reservationDate, String status,
				double totalPrice, PassengerProfile passengerProfile) {
			super();
			ReservationId = reservationId;
			this.user = user;
			this.flight = flight;
			this.reservationDate = reservationDate;
			this.status = status;
			this.totalPrice = totalPrice;
			this.passengerProfile = passengerProfile;
		}

		

		
		
		
		public Long getReservationId() {
			return ReservationId;
		}






		public void setReservationId(Long reservationId) {
			ReservationId = reservationId;
		}






		public Users getUser() {
			return user;
		}






		public void setUser(Users user) {
			this.user = user;
		}






		public Flight getFlight() {
			return flight;
		}






		public void setFlight(Flight flight) {
			this.flight = flight;
		}






		public Date getReservationDate() {
			return reservationDate;
		}






		public void setReservationDate(Date reservationDate) {
			this.reservationDate = reservationDate;
		}






		public String getStatus() {
			return status;
		}






		public void setStatus(String status) {
			this.status = status;
		}






		public double getTotalPrice() {
			return totalPrice;
		}






		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}






		public PassengerProfile getPassengerProfile() {
			return passengerProfile;
		}






		public void setPassengerProfile(PassengerProfile passengerProfile) {
			this.passengerProfile = passengerProfile;
		}






		@Override
		public String toString() {
			return "Reservations [ReservationId=" + ReservationId + ", user=" + user + ", flight=" + flight
					+ ", reservationDate=" + reservationDate + ", status=" + status + ", totalPrice=" + totalPrice
					+ ", passengerProfile=" + passengerProfile + "]";
		}






	    
	    
	    
	    
}*/
