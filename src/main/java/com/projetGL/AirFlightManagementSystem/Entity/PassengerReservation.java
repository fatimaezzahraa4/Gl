package com.projetGL.AirFlightManagementSystem.Entity;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "flight"})
})
public class PassengerReservation implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	
	@ManyToOne( cascade = CascadeType.ALL)
   @JoinColumn(name = "userId", referencedColumnName = "user_account_id")
	private PassengerProfile userId;

	
	  
	
	  @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "flight", nullable = false ,referencedColumnName = "flightId") // Nom de la colonne doit correspondre
	    private Flight flight;
	  
	  
	@DateTimeFormat(pattern= "dd-MM-yyyy")
	private Date reservationDate;

	

	public PassengerReservation(Integer id, PassengerProfile userId, Flight flight, Date reservationDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.flight = flight;
		this.reservationDate = reservationDate;
	}


	public PassengerReservation() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public PassengerProfile getUserId() {
		return userId;
	}


	public void setUserId(PassengerProfile userId) {
		this.userId = userId;
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


	@Override
	public String toString() {
		return "PassengerReservation [id=" + id + ", userId=" + userId + ", flight=" + flight + ", reservarionDate="
				+ reservationDate + "]";
	}


	
	
	
}
